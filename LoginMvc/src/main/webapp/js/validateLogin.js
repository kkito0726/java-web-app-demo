const submit = document.getElementById("SUBMIT");

submit.onclick = () => {
	const userId = document.getElementById("ID_USER_ID");
	const password = document.getElementById("USER_PASSWORD");
	let isSuccess = true;
	if (userId.value == "" || password.value == "") {
		isSuccess = false;
		alert("入力漏れがあります");
	}
	
	return isSuccess;
}