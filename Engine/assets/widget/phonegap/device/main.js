// 等待加载PhoneGap    
	document.addEventListener("deviceready", onDeviceReady, false);
	
	// PhoneGap加载完成 
	function onDeviceReady() {

		var element = document.getElementById('deviceProperties');

		element.innerHTML = 'Device Name: '     + device.name     + '<br />' + 
							'Device PhoneGap: ' + device.cordova + '<br />' + 
							'Device Platform: ' + device.platform + '<br />' + 
							'Device UUID: '     + device.uuid     + '<br />' + 
							'Device Version: '  + device.version  + '<br />';
	}
