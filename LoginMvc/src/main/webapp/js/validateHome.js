const submit = document.getElementById("SUBMIT");

submit.onclick = () => {
	const age = document.getElementById("AGE");
	const message = document.getElementById("MESSAGE");
	
	let isSubmit = true;
	if (!isNaN(age.value) || message.value == "") {
		isSubmit = false;
		alert("入力ミスがあります")
	}
	
	return isSubmit;
}