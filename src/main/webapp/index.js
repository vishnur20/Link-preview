// app.js

const fitData = (jsonData) => {
	
	$('#card-details').attr('class', 'card');
	
	let imageElement = document.getElementById('og-image');
	let titleElement = document.getElementById('og-title');
	let descriptionElement = document.getElementById('og-description');
	let linkElement = document.getElementById('og-url');
	
	imageElement.setAttribute('src', jsonData.ogImage === '' ? 'No data' : jsonData.ogImage);
	titleElement.innerHTML = jsonData.ogTitle === '' ? 'No data' : jsonData.ogTitle;
	descriptionElement.innerHTML = jsonData.ogDescription === '' ? 'No data' : jsonData.ogDescription;
	linkElement.setAttribute('href', jsonData.ogURL === '' ? $('#url').val() : jsonData.ogURL);
	linkElement.innerHTML = jsonData.ogURL === '' ? $('#url').val() : jsonData.ogURL;
}

$(document).ready(() => {
	$('#submit').click(() => {
		$('#status').text('Loading...');
		$('#card-details').css({ 'display': 'none' });
		
		const url = $('#url').val();

        const regex = /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:[/?#]\S*)?$/i;
        if(!regex.test(url) || url === '') {
            alert('Enter valid URL.');
            $('#status').text('');
            return;
        }

		const encodedURL = encodeURIComponent(url);
		$.ajax({
			url: './getPreview?url=' + encodedURL,
			type: 'GET',			
			success: (data) => {
				$('#status').text('');
				$('#card-details').css({ 'display': 'block' });
				
				if(data === "can't connect") {
					alert('Site refuse to share meta data!\n Try other sites.');
					return;
				}
				/*else if('connection timed out') {
					alert('Connection Timed Out!');
					return;
				}*/
				
				console.log(data);
				fitData(data);	// fit data in the template
			}
		});
	});
});


