# DICE-Platform

This is the DICE Platform repository. It includes all the necessary plugins and features to build the DICE IDE.

DICE IDE is based on Eclipse Mars 4.5.1.

If you want to build your own DICE IDE, you need to follow these steps:

* Download the Eclipse base, concretely the Modelling distribution: http://www.eclipse.org/downloads/packages/eclipse-modeling-tools/mars1
* Checkout this repository to your computer
* Start Eclipse, create a new workspace and import all the plugins:
	* Right click to the Resource Explorer
	* Import
	* General/Existing Projects into Workspace
	* Select your GIT folder (where you checked out this project)
	* Select all the projects
	* Uncheck "Copy projects into workspace"
	* Finish
* Now you need to select the DICE Target Platform. It is placed on the 'org.dice.rcp' plugin (dice.target file):
	* Window --> Preferences
	* Browse to Plug-in Development --> Target Platform
	* DICE Target Platform should appear here automatically. Click on Reload button in order to import all the required plugins
* Next step is to check if all the plugin dependencies are correctly loaded:
	* Go to 'org.dice.rcp' plugin
	* Open 'dice.product' file
	* Click on Overview Tab
	* Click on 'Launch an Eclipse application'
		* If all worked fine, you'll se the DICE RCP launched
		* If any error occurred, it appears on the Console view (Window --> Show View --> Console). Open it before launching the application
* Final step is to build your RCP. You can build DICE IDE for multiple platforms:
	* Go to 'org.dice.rcp' plugin
	* Open 'dice.product' file
	* Click on Overview Tab
	* Click on "Eclipse Product export wizard"
	* Fill the wizard as following:
		* Configuration: /org.dice.rcp/dice.product
		* Root directory: dice
		* Check synchronize before exporting
		* Destination directory: $HOME\dice (this folder will include a folder for every platform. Also, you can choose Archive file if you want to export it into a ZIP file)
		* Uncheck "Export source" and "Generate p2 repository"
		* Check "Export for multiple platforms" and "Allow for binary cycles in target platform"
		* If you choose "Export for multiple platform", you will choose which platforms you want to export to in the next step
		* By default, your platform will be chosen, but you can choose others. Recommended are these ones:
			* Linux (gtk/x86)
			* Linux (gtk/x86_64)
			* Macosx (cocoa/x86_64)
			* Win32 (win32/x86)
			* Win32 (win32/x86_64)
		* The build process will take 1.5 minutes approximately per platform

# Exporting for Mac platform

If you want to build your own product, and your target platform is MacOSX, there is a bug on the build process, at least doing it from a Windows platform (not tested on Mac platform, maybe it works fine).

After the build process is completed, you need to go to the generated folder and check the folder structure, because there are some file and folder not placed in the correct location.

The generated folder structure looks as follows:
- macosx.cocoa.x86_64
	- dice
		- configuration
		- DICE.app
			- Contents
				- MacOS
					- DICE.ini
		- features
		- MacOS
			- DICE
		- plugins
		- Resources
		- .eclipseproduct
		- Info.plist

You need to modify it as follows:
- macosx.cocoa.x86_64
	- dice
		- configuration
		- DICE.app
			- Contents
				- MacOS
					- DICE
					- DICE.ini
				- Resources
				- Info.plist
		- features
		- plugins
		- .eclipseproduct
