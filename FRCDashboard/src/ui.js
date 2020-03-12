// Define UI elements
let ui = {
    timer: document.getElementById('timer'),
	robotState: document.getElementById('robot-state').firstChild,

    autoSelect: document.getElementById('auto-select'),

	spin: document.getElementById('spin'),
	findRed: document.getElementById('red'),
	findBlue: document.getElementById('blue'),
	findGreen: document.getElementById('green'),
	findYellow: document.getElementById('yellow'),
	wheelAction: document.getElementById('wheel-action'),
	wheelDeployed: document.getElementById('wheel-deploy'),
	wheelColour: document.getElementById('wheel-colour'),
	
	climb: {
		armed: document.getElementById('climb-armed'),
		readout: document.getElementById('climb-readout')
	},
	dump: document.getElementById('dumpTruck'),
	dumpToggle: document.getElementById('dump')
};


// Key Listeners
NetworkTables.addKeyListener('/robot/time', (key, value) => {
    // This is an example of how a dashboard could display the remaining time in a match.
    // We assume here that value is an integer representing the number of seconds left.
    ui.timer.textContent = value < 0 ? '0:00' : Math.floor(value / 60) + ':' + (value % 60 < 10 ? '0' : '') + value % 60;
});

// Load list of prewritten autonomous modes
NetworkTables.addKeyListener('/SmartDashboard/autonomous/modes', (key, value) => {
    // Clear previous list
    while (ui.autoSelect.firstChild) {
        ui.autoSelect.removeChild(ui.autoSelect.firstChild);
    }
    // Make an option for each autonomous mode and put it in the selector
    for (let i = 0; i < value.length; i++) {
        var option = document.createElement('option');
        option.appendChild(document.createTextNode(value[i]));
        ui.autoSelect.appendChild(option);
    }
    // Set value to the already-selected mode. If there is none, nothing will happen.
    ui.autoSelect.value = NetworkTables.getValue('/SmartDashboard/currentlySelectedMode');
});

// Load list of prewritten autonomous modes
NetworkTables.addKeyListener('/SmartDashboard/autonomous/selected', (key, value) => {
    ui.autoSelect.value = value;
});


// Update NetworkTables when autonomous selector is changed
ui.autoSelect.onchange = function() {
    NetworkTables.putValue('/SmartDashboard/autonomous/selected', this.value);
};


// Colour Wheel Selection Code
ui.spin.onclick = function() {
	ui.wheelAction.innerHTML="Spin 3 Times";
	NetworkTables.putValue('/SmartDashboard/wheel_function', this.value);
}
ui.findRed.onclick = function() {
	ui.wheelAction.innerHTML="Finding " + this.value;
	NetworkTables.putValue('/SmartDashboard/wheel_function', this.value);
}
ui.findBlue.onclick = function() {
	ui.wheelAction.innerHTML="Finding " + this.value;
	NetworkTables.putValue('/SmartDashboard/wheel_function', this.value);
}
ui.findGreen.onclick = function() {
	ui.wheelAction.innerHTML="Finding " + this.value;
	NetworkTables.putValue('/SmartDashboard/wheel_function', this.value);
}
ui.findYellow.onclick = function() {
	ui.wheelAction.innerHTML="Finding " + this.value;
	NetworkTables.putValue('/SmartDashboard/wheel_function', this.value);
}

NetworkTables.addKeyListener('/SmartDashboard/wheel_colour', (key, value) => {
	var message = value;
	ui.wheelColour.innerHTML=message;
	ui.wheelColour.style.background=message;
});

// Listen for "Spinner Deployed" and report.
NetworkTables.addKeyListener('/SmartDashboard/wheel_deployed', (key, value) => {
	if (value) {
		var message = "Deployed";
		ui.wheelDeployed.style.background="#E00";
	} else {
		var message = "Retracted";
		ui.wheelDeployed.style.background="#0E0";
	}
	ui.wheelDeployed.innerHTML = message;
});

// Lifting Mechanism Arming
ui.climb.armed.onclick = function() {
	NetworkTables.putValue('/SmartDashboard/climb', ui.climb.armed.checked);
}

NetworkTables.addKeyListener('/SmartDashboard/climb', (key, value) => {
	if (value) {
		var message = "LIFT IS ARMED";
		ui.climb.readout.style.background="#E00";
	} else {
		var message = "Lift is NOT armed";
		ui.climb.readout.style.background="#000";
	}
	ui.climb.readout.innerHTML = message;
});

// DumpTruck Lifter Code (Visual SVG update, and "test" checkbox
ui.dumpToggle.onclick = function() {
	NetworkTables.putValue('/SmartDashboard/dumpTruck', ui.dumpToggle.checked);
}

NetworkTables.addKeyListener('/SmartDashboard/dumpTruck', (key, value) => {
	if (value) {
		ui.dump.style.transform = "rotate(0deg)";
	} else {
		ui.dump.style.transform = "rotate(-30deg)";
	}
});
addEventListener('error',(ev)=>{
    ipc.send('windowError',{mesg:ev.message,file:ev.filename,lineNumber:ev.lineno})
})
