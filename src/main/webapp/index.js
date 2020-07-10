var error = document.getElementById('error');

function getHeatmap(){
	error.style.display = "none";
	var fromDate = document.getElementById("fromDate").value;
	var fromTime = document.getElementById("fromTime").value;
	var toDate = document.getElementById("toDate").value;
	var toTime = document.getElementById("toTime").value;
	
	if (fromDate != "" || fromTime != "" || toDate != "" || toTime != ""){
		fetch('restservices/heatmap', {
		method: 'POST',
		body: JSON.stringify({
			fromDate: fromDate,
			fromTime: fromTime,
			toDate: toDate,
			toTime: toTime
		}),
		headers:{
			'Content-Type': 'application/json'
		}})
		.then(response => response.json())
		.then(function(myJson){
			console.log(myJson)
			for (const success of myJson){
				if (success.succes = true){
					document.getElementById("picture").src = "/innovationFiles/huiskamer.png";
				}
			}
		})
	} else {
		error.style.display = "block";
		error.innerHTML = "Fill in the dates and times!"
		
	}
	
}
