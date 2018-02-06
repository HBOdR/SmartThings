/**
 *  Insteon Dimmer Device Handler
 *  
 *  Copyright 2018 Hugo Bonilla
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

metadata {
	definition (name: "Insteon Dimmer", namespace: "HBOdR", author: "Hugo Bonilla") {
		capability "Polling"
		capability "Refresh"
		capability "Switch"
		capability "Switch Level"
    }

    // simulator metadata
    simulator {}

    // UI tile definitions
	tiles(scale:2) {
		multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true){
			tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label:'${name}', action:"switch.off", icon:"st.switches.switch.on", backgroundColor:"#79b821", nextState:"turningOff"
				attributeState "off", label:'${name}', action:"switch.on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"turningOn"
				attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.switches.switch.on", backgroundColor:"#79b821", nextState:"turningOff"
				attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"turningOn"
            }
            
			tileAttribute ("device.level", key: "SLIDER_CONTROL") {
				attributeState "level", action:"switch level.setLevel"
           }
        }
        
		standardTile("refresh", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
        }

        main(["switch"])
        details(["switch", "level", "refresh"])
    }
}

// Parse incoming device messages to generate events - doesn't seem to be working.
def parse(String description) {
    log.debug "parse description: $description"

    def attrName = null
    def attrValue = null

    if (description?.startsWith("on/off:")) {
        log.debug "switch command"
        attrName = "switch"
        attrValue = description?.endsWith("1") ? "on" : "off"
    }

    def result = createEvent(name: attrName, value: attrValue)

    log.debug "Parse returned ${result?.descriptionText}"
    return result
}

def on() {
	log.info "Turning $device.name (${device.deviceNetworkId}) ON"
	parent.childOn(device.deviceNetworkId)
	sendEvent(name: "switch", value: "on");
}

def off() {
	log.info "Turning $device.name (${device.deviceNetworkId}) OFF"
	parent.childOff(device.deviceNetworkId)
	sendEvent(name: "switch", value: "off");
}

def setLevel(level) {
	if (level > 0) {
		parent.childDim(device.deviceNetworkId, level)
		sendEvent(name: "switch", value: "on")
		sendEvent(name: "level", value: level, unit: "%")
	} else {
		parent.childOff(device.deviceNetworkId)
		sendEvent(name: "switch", value: "off")
	}
}

def ping() {
    log.debug "ping()"
    parent.childStatus(device.deviceNetworkId)
}

def poll() {
    log.debug "poll()"
    parent.childStatus(device.deviceNetworkId)
}

def refresh() {
    log.debug "refresh()"
    parent.childStatus(device.deviceNetworkId)
}

def updatestatus(level) {
	log.debug "updatestatus(${device.displayName}, ${level})"
	if (level > 0) {
		sendEvent(name: "switch", value: "on")
		sendEvent(name: "level", value: level, unit: "%")
	} else {
		sendEvent(name: "switch", value: "off")
	}
}
