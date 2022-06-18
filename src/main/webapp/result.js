
$(document).ready(() => {
	loadData();
});


// getting result from the server using AJAX
function loadData() {
	$.ajax({
		url: './result',
		type: 'GET',
		success: (results) => {
			console.log(results);
			
			results.forEach(shortURLData => {
				let div_result = document.createElement('div');
				div_result.setAttribute('class', 'eachResult');
				// div_result.setAttribute('data-id', shortURLData.shortURL);
				
				// creating elements
				let div_shortURL = document.createElement('div');
				let div_userURL = document.createElement('div');
				let div_clicks = document.createElement('div');
				let btn_showUserAgents = document.createElement('button');
				let div_userAgents = document.createElement('div');
				const br = document.createElement('br');
				let btn_deactivate = document.createElement('button');
				
				// adding innerHTML and setting properties
				div_shortURL.innerHTML = '<b>Short URL: </b>' + shortURLData.shortURL;
				div_userURL.innerHTML = '<b>User URL: </b>' + shortURLData.userURL;
				div_clicks.innerHTML = '<b>Clicks: </b>' + shortURLData.clicks;
				btn_showUserAgents.innerHTML = 'Show user-agents';
				btn_showUserAgents.setAttribute('class', 'btn-primary');
				
				btn_deactivate.innerHTML = 'Deactivate';
				btn_deactivate.setAttribute('deactivate-id', shortURLData.shortURL);
				btn_deactivate.addEventListener('click', deactivateHandler);
				btn_deactivate.setAttribute('class', 'btn-danger');
				div_userAgents.innerHTML = '<b>User agents: </b><br/>' + shortURLData.userAgents;
				div_userAgents.setAttribute('user-agent-id', shortURLData.shortURL);
				//div_userAgents.style.display = 'none';
				
				// adding elements
				div_result.appendChild(div_shortURL);
				div_result.appendChild(div_userURL);
				div_result.appendChild(div_clicks);
				div_result.appendChild(btn_showUserAgents);
				div_result.appendChild(btn_deactivate);
				div_result.appendChild(div_userAgents);
				
				document.getElementById('results').appendChild(div_result);
				document.getElementById('results').appendChild(br);
			});
		}
	});
}

/* function showUserAgents() {
	this.innerHTML = 'Hide user-agents';
} */

function deactivateHandler() {
	const shortURL = this.getAttribute('deactivate-id');
	const encodedShortURL = encodeURIComponent(shortURL);
	
	$.ajax({
		url: './result/deactivate?shortURL=' + encodedShortURL,
		type: 'GET',
		success: (data) => {
			console.log(data);
			// window.alert(shortURL + 'is Deactivated');
		}
	});
	
	this.parentNode.remove();
}
