# azurehackathon
Azure Hackathon 2019 Shanghai


**Environment Setup:**

Extentions are build on 1811.9

1. Use installer b2c_acc by running : ./install.sh -r b2c_acc
2. Generate accelerator module by using the yacceleratorstorefront:  ant modulegen -Dinput.module=accelerator -Dinput.name=AzureHackathon -Dinput.package=de.hybris.azurehackathon -Dinput.template=develop
3. reinstall all ootb addons on the created storefront: ant reinstall_addons -Dtarget.storefront=AzureHackathonstorefront
4. rebuild the sysem: ant clean all
5. Initializ the system: ant initialize
