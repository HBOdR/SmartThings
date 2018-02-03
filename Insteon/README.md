# SmartThings/Insteon

SmartThing SmartApps and Device Handlers for Insteon Switches and Dimmers.

This solution allows a way for SmartThings send commands to the Insteon Hub and receive responses. You can control your Insteon switchwes and dimmers from within ST and use various automation solutions within ST.

In this solution the Insteon Hub settings and device mappings are done using the SmartThings app.

Supported Devices and Functionality
* Insteon Switches and Dimmers -  on/off/level is supported

Setup
-------------------------------------
**Step 1: Install the SmartApps and Device Handler in Smartthings**
You will need to install the following SmartApps and DTH - make sure you install all 4
* insteon-device-manager-app.groovy
* insteon-switch-child-app.groovy
* insteon-dimmer-device.groovy
* insteon-switch-device.groovy

You can download them from here:

https://github.com/HBOdR/SmartThings/blob/master/Insteon/SmartApps/insteon-device-manager-app.groovy

https://github.com/HBOdR/SmartThings/blob/master/Insteon/SmartApps/insteon-switch-child-app.groovy

https://github.com/HBOdR/SmartThings/blob/master/Insteon/DeviceHandlers/insteon-dimmer-device.groovy

https://github.com/HBOdR/SmartThings/blob/master/Insteon/DeviceHandlers/insteon-switch-device.groovy


**Step 2: SmartApp Setup and Adding Switches**
* Install the SmartApp from your phone.
  * You will need to select the ST hub AND configure IP address, port, username and password for the local Insteon hub.

* You can then add Insteon switches on Smartthings and set their Device ID.
