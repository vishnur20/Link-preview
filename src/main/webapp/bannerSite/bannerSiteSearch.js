
// take user to specified page

$(document).ready(() => {
	$('#create-banner-site').click(() => {
		redirectToCreate();
	});
});


function redirectToCreate() {
	window.location.href = 'http://localhost:8181/LinkPreviewer/bannerSite/createBannerSite.html';
}