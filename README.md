# azurehackathon
Azure Hackathon 2019 Shanghai


**Environment Setup:**

Extentions are built on 1811.9

1. Use installer b2c_acc by running : *./install.sh -r b2c_acc*
2. Generate accelerator module by using the yacceleratorstorefront:  *ant modulegen -Dinput.module=accelerator -Dinput.name=AzureHackathon -Dinput.package=de.hybris.azurehackathon -Dinput.template=develop*
3. reinstall all ootb addons on the created storefront: *ant reinstall_addons -Dtarget.storefront=AzureHackathonstorefront*
4. rebuild the sysem: *ant clean all*
5. Initializ the system: *ant initialize*


**Integrate Azure Speech Recognition:**

1. go to [Azure Speech Recognition](https://azure.microsoft.com/en-us/services/cognitive-services/speaker-recognition/) to activate the azure service for your account and get the Endpoint and key.
2. Since we extends the OOTB SearchBoxComponent for additional speach recognition of search functionality on the storefront, we added following lines in the AzureHackathoncore-items.xml allowing for set apikey as well as region where your service located in the cms component:

        <itemtype code="AzureHackthonSearchBoxComponent" extends="SearchBoxComponent"
        autocreate="true" generate="true"
        jaloclass="de.hybris.azurehackathon.core.jalo.AzureHackthonSearchBoxComponent">
        <description>Represents the search box component using Azure Voice Recognition.</description>
        <attributes>
        <attribute qualifier="apikey" type="java.lang.String">
        <description>API_Key for consuming the Azure Service</description>
        <modifiers/>
        <persistence type="property"/>
        </attribute>
        <attribute qualifier="region" type="java.lang.String">
        <description>region of the Azure Service</description>
        <modifiers/>
        <persistence type="property"/>
        </attribute>
        </attributes>
        </itemtype>

3. The frontend code is implemented in AzureHackathon/AzureHackathonstorefront/web/webroot/WEB-INF/views/responsive/cms/azurehackthonsearchboxcomponent.jsp for new button and logic for calling the Speech Recognition service.
4. Create a AzureHackthonSearchBoxComponent in backoffice or via impex like:
           *insert_update AzureHackthonSearchBoxComponent;&Item;apikey;catalogVersion(catalog(id),version)[unique=true,allownull=true];creationtime[forceWrite=true,dateformat=dd.MM.yyyy hh:mm:ss];displayProductImages[allownull=true];displayProducts[allownull=true];displaySuggestions[allownull=true];maxProducts[allownull=true];maxSuggestions[allownull=true];minCharactersBeforeRequest[allownull=true];modifiedtime[dateformat=dd.MM.yyyy hh:mm:ss];name;onlyOneRestrictionMustApply[allownull=true];owner(&Item)[forceWrite=true];region;uid[unique=true,allownull=true];visible[allownull=true];waitTimeBeforeRequest[allownull=true]
            ;Item1;xxxxxxxxxxxxx;electronicsContentCatalog:Online;05.09.2019 02:37:51;false;true;true;4;6;3;05.09.2019 03:46:23;Azurehackathon Search Box;false;;westus;AzureHackathonSearchBox;true;500*

5. Go to the smartedit and replace the ootb SearchBoxComponent on the SearchBoxSlot  of homepage of electronics content catalog:online with the AzureHackthonSearchBoxComponent or via Impex:
            *insert_update ElementsForSlot;&Item;creationtime[forceWrite=true,dateformat=dd.MM.yyyy hh:mm:ss];language(isocode)[unique=true];modifiedtime[dateformat=dd.MM.yyyy hh:mm:ss];owner(&Item)[forceWrite=true];qualifier;reverseSequenceNumber;sequenceNumber;source(catalogVersion(catalog(id),version),uid)[unique=true,allownull=true];target(catalogVersion(catalog(id),version),uid)[unique=true,allownull=true]
            ;Item589;05.09.2019 02:37:51;;05.09.2019 02:37:51;;ElementsForSlot;765,461,655;0;electronicsContentCatalog:Online:SearchBoxSlot;electronicsContentCatalog:Online:AzureHackathonSearchBox*

6. Then you should see the Azure Speech Recognition integration on the storefront.
