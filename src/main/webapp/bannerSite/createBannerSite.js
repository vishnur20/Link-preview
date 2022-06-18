
// saving the banner details in database(map)

$(document).ready(() => {
	$("#save").click(() => {
		saveBanner();
	});
});

function saveBanner() {
	const title = $('#title').val();
	const description = $('#description').val();
	const url = $('#url').val();
	
	const bannerObj = {
		title: title,
		description: description,
		url: url
	};
	
	const jsonBannerObj = JSON.stringify(bannerObj);
	console.log(jsonBannerObj);
	
	$.ajax({
		url: './bannerSiteSaver',
		type: 'POST',
		contentType: 'application/json',
		data: jsonBannerObj,	// post json data
		success: (data) => {
			console.log("Saved.");
		}
	});
}