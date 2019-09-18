/**
 *
 */
package de.hybris.azurehackathon.storefront.controllers.azurehackathon;

import de.hybris.azurehackathon.core.util.SpeakerVerificationRestClient;
import de.hybris.azurehackathon.core.util.contract.verification.CreateProfileResponse;
import de.hybris.azurehackathon.core.util.contract.verification.Enrollment;
import de.hybris.azurehackathon.core.util.contract.verification.PhrasesException;
import de.hybris.azurehackathon.core.util.contract.verification.VerificationPhrase;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.AbstractController;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author i314119
 *
 */
@Controller
@RequestMapping(value = "/speakerrecognition")

public class SpeakerRecognitionController extends AbstractController
{
	@Resource(name = "configurationService")
	private ConfigurationService configurationService;

	@RequestMapping(value = "/enrollnewverificationprofile", method =
	{ RequestMethod.POST, RequestMethod.OPTIONS }, produces =
	{ MediaType.APPLICATION_JSON_VALUE })
	//@ResponseBody
	public ResponseEntity<String> enrollNewVerificationProfile(@RequestParam(value = "locale", defaultValue = "en-US")
	final String locale, final MultipartFile file, final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final RedirectAttributes redirectModel) throws IOException

	{
		System.out.println(request.getMethod());

		if (request.getMethod().equals("OPTIONS"))
		{
			//In case of an OPTIONS, we allow the access to the origin of the petition

			final String vlsOrigin = request.getHeader("ORIGIN");
			final HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("MyResponseHeader", "MyValue");
			responseHeaders.set("Access-Control-Allow-Origin", vlsOrigin);
			responseHeaders.set("Access-Control-Allow-Methods", "POST");
			responseHeaders.set("Access-Control-Allow-Headers", "accept, content-type");
			responseHeaders.set("Access-Control-Max-Age", "1728000");
			return new ResponseEntity<String>("ok", responseHeaders, HttpStatus.OK);
		}
		else
		{
			if (file != null)
			{
				System.out.println(file.getContentType());
				System.out.println(file.getSize());
			}

			final String endpoint = configurationService.getConfiguration()
					.getString("azurehackthon2019.speakerrecognition.endpoint");
			final String key = configurationService.getConfiguration().getString("azurehackthon2019.speakerrecognition.key");
			final SpeakerVerificationRestClient sv = new SpeakerVerificationRestClient(endpoint, key);
			//final PrintWriter out = response.getWriter();
			final ObjectMapper mapper = new ObjectMapper();
			try
			{
				final CreateProfileResponse cpr = sv.createProfile(locale);
				System.out.println(cpr.verificationProfileId.toString());
				final Enrollment enrollment = sv.enroll(file.getInputStream(), cpr.verificationProfileId);
				final String jsonString = mapper.writeValueAsString(enrollment);
				//out.write(jsonString);
				return new ResponseEntity<String>(jsonString, HttpStatus.OK);
			}
			catch (final Exception e)
			{
				// XXX Auto-generated catch block
				e.printStackTrace();
				//out.write(mapper.writeValueAsString(e));
				return new ResponseEntity<String>(mapper.writeValueAsString(e), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}


		//this.getBean(request, "ConfigurationService", beanType);
		//System.out.println(configurationService.getConfiguration().getString("azurehackthon2019.inkrecognizer.key"));
		//final PrintWriter out = response.getWriter();
		//return new ResponseEntity<String>("hahaha", HttpStatus.OK);
	}


	@RequestMapping(value = "/getverificationphrases", method = RequestMethod.GET, produces =
	{ MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public void getVerificationPhrases(@RequestParam(value = "locale", defaultValue = "en-US")
	final String locale, final Model model, final HttpServletRequest request, final HttpServletResponse response,
			final RedirectAttributes redirectModel) throws IOException

	{

		//this.getBean(request, "ConfigurationService", beanType);
		final String endpoint = configurationService.getConfiguration().getString("azurehackthon2019.speakerrecognition.endpoint");
		final String key = configurationService.getConfiguration().getString("azurehackthon2019.speakerrecognition.key");
		final SpeakerVerificationRestClient sv = new SpeakerVerificationRestClient(endpoint, key);
		final PrintWriter out = response.getWriter();
		final ObjectMapper mapper = new ObjectMapper();
		try
		{
			final List<VerificationPhrase> phrases = sv.getPhrases(locale);

			final String jsonString = mapper.writeValueAsString(phrases);
			out.write(jsonString);
		}
		catch (final PhrasesException e)
		{
			// XXX Auto-generated catch block
			e.printStackTrace();
			out.write(mapper.writeValueAsString(e));
		}
		catch (final URISyntaxException e)
		{
			// XXX Auto-generated catch block
			e.printStackTrace();
			out.write(mapper.writeValueAsString(e));
		}

		//return new ResponseEntity<String>("hahaha", HttpStatus.OK);
	}

}
