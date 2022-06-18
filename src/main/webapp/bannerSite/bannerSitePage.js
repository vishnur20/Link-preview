
// 

$(document).ready(() => {
	getUserDetails();
});


function getUserDetails() {
	$('#banner-button').click(() => {
		const fName = window.prompt("First Name: ");
		const lName = window.prompt("Last Name: ");
		const age = window.prompt("Age: ");
		const iframeElement = document.getElementById('site-window');
		const url = iframeElement.getAttribute('src');
		console.log(url);
		
		const user = {
			fName: fName,
			lName: lName,
			age: age,
			url: url
		};
		
		const jsonUser = JSON.stringify(user);
		
		// sending data to database(hashmap)
		$.ajax({
			url: './userSaver',
			type: 'POST',
			contentType: 'json',
			data: jsonUser,
			success: (data) => {
				console.log(data);
			}
		});
	});
}
