
// 유효성 검사에 사용할 정규표현식
const reUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
const rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
const reName  = /^[가-힣]{2,10}$/
const reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

// 유효성 검사에 사용할 상태변수
let isUidOk   = false;
let isPassOk  = false;
let isNameOk  = false;
let isNickOk  = false;
let isEmailOk = false;
let isHpOk    = false;


document.addEventListener('DOMContentLoaded', function() {

	const post= document.getElementById('post')
	post.addEventListener('click',function (){
		postcode();
	})

	const btnCheckUid = document.getElementById('btnCheckUid');
	const btnCheckNick = document.getElementById('btnCheckNick');
	const btnSendEmail = document.getElementById('btnSendEmail');
	const btnAuthEmail = document.getElementById('btnAuthEmail');

	const registerForm = document.getElementsByTagName('form')[0];

	const resultId = document.getElementsByClassName('resultId')[0];
	const resultPass = document.getElementsByClassName('resultPass')[0];
	const resultName = document.getElementsByClassName('resultName')[0];
	const resultNick = document.getElementsByClassName('resultNick')[0];
	const resultEmail = document.getElementsByClassName('resultEmail')[0];
	const resultHp = document.getElementsByClassName('resultHp')[0];
	const auth = document.getElementsByClassName('auth')[0];


	// 1.아이디 유효성 검사
	btnCheckUid.onclick = function() {

		const uid = registerForm.uid.value;

		// 아이디 유효성 검사
		if (!uid.match(reUid)) {
			resultId.innerText = '아이디가 유효하지 않습니다.';
			resultId.style.color = 'red';
			return;
		}
		checkUserIdAvailability(uid);
	}
	// 중복체크
	function checkUserIdAvailability(uid) {
		console.log(uid);
		fetch('/sboard/user/checkUser?uid=' + uid)
			.then(response => {
				if (!response.ok) {
					throw new Error('서버 응답 오류');
				}
				return response.json();
			})
			.then(data => {
				console.log(data)
				if (data) {
					resultId.innerText ='이미 사용중인 아이디입니다.';
					resultId.style.color = 'red';
					isUidOk = false;
				} else {
					resultId.innerText ='사용 가능한 아이디입니다.';
					resultId.style.color = 'green';
					isUidOk = true;
				}
			})
			.catch(error => {
				console.error('에러:', error);
				showResult('아이디 확인 중 오류가 발생했습니다.', 'red');
			});
	}


	// 2.비밀번호 유효성 검사
	registerForm.pass2.addEventListener('focusout', function(){

		const pass = registerForm.pass.value;
		const pass2 = registerForm.pass2.value;

		if(!pass.match(rePass)){
			resultPass.innerText = "비밀번호가 유효하지 않습니다.";
			resultPass.style.color = 'red';
			return;
		}

		if(pass == pass2){
			resultPass.innerText = "비밀번호가 일치합니다.";
			resultPass.style.color = 'green';
			isPassOk = true;
		}else{
			resultPass.innerText = "비밀번호가 일치하지 않습니다.";
			resultPass.style.color = 'red';
			isPassOk = false;
		}
	});

	// 3.이름 유효성 검사
	registerForm.name.addEventListener('focusout', function(){

		const name = registerForm.name.value;

		if(!name.match(reName)){
			resultName.innerText = "이름이 유효하지 않습니다.";
			resultName.style.color = 'red';
			isNameOk = false;
		}else{
			resultName.innerText = "";
			isNameOk = true;
		}
	});

	// 4.별명 유효성 검사
	btnCheckNick.addEventListener('click',function (){
		const nick = registerForm.nick.value;
		const nickPram = encodeURI(encodeURIComponent(nick));

		if(!nick.match(reNick)){
			resultNick.innerText = '별명이 유효하지 않습니다.';
			resultNick.style.color = 'red';
			return;
		}else{
			resultNick.innerText = '별명이 유효합니다.';
		}
		console.log(nick);
		checkUserNick(nickPram);
	})
	function checkUserNick(nick){
		fetch('/sboard/user/checkUserNick?nick='+nick)
			.then(response => {
				if (!response.ok) {
					throw new Error('서버 응답 오류');
				}
				return response.json();
			})
			.then(data => {
				console.log("data"+data);
				if(data.result > 0){
					resultNick.innerText = '이미 사용중인 별명입니다.';
					resultNick.style.color = 'red';
					isNickOk = false;
				}else{
					resultNick.innerText = '사용 가능한 별명입니다.';
					resultNick.style.color = 'green';
					isNickOk = true;
				}
			}).catch(err => {
			console.log(err);
		});


	}


	// 5.이메일 유효성 검사
	let preventDblClick = false;

	btnSendEmail.onclick = async function(){

		const email = registerForm.email.value;
		console.log(email)
		// 이중 클릭 방지
		if(preventDblClick){
			return;
		}

		// 이메일 유효성 검사
		if(!email.match(reEmail)){
			resultEmail.innerText = '유효한 이메일이 아닙니다.';
			resultEmail.style.color = 'red';
			return;
		}else{
			resultEmail.innerText = '유효한 이메일입니다.';
			resultEmail.style.color = 'green';
		}

		try{
			preventDblClick = true;

			const response = await fetch('/sboard/user/checkUserEmail?email='+email);
			const data = await response.json();
			console.log(data);

			if(data){
				resultEmail.innerText = '이미 사용중인 이메일 입니다.';
				resultEmail.style.color = 'red';
				isEmailOk = false;
			}else{
				resultEmail.innerText = '이메일 인증코드를 확인 하세요.';
				resultEmail.style.color = 'green';
				auth.style.display = 'block';
			}


		}catch(e){
			console.log(e);
		}
	}

	btnAuthEmail.onclick = function(){

		const code = registerForm.auth.value;

		fetch('/sboard/user/checkUserEmail', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
			body: JSON.stringify({"code":code})
		})
			.then(resp => resp.json())
			.then(data => {
				console.log(data);

				if(data.result > 0){
					resultEmail.innerText = '이메일이 인증되었습니다.';
					resultEmail.style.color = 'green';
					isEmailOk = true;
				}else{
					resultEmail.innerText = `유효한 인증코드가 아닙니다.${code}`;
					resultEmail.style.color = 'red';
					isEmailOk = false;
				}
			})
			.catch(err => {
				console.log(err);
			});


	}




	// 6.휴대폰 유효성 검사
	registerForm.hp.addEventListener('focusout', async function(){

		const value = registerForm.hp.value;
		const type = 'hp'

		if(!hp.match(reHp)){
			resultHp.innerText = '전화번호가 유효하지 않습니다.';
			resultHp.style.color = 'red';
			return;
		}

		try{
			const response = await fetch(`/sboard/user/${type}/${value}`);
			const data = await response.json();
			console.log(data);

			if(data.result > 0){
				resultHp.innerText = '이미 사용중인 휴대폰번호 입니다.';
				resultHp.style.color = 'red';
				isHpOk = false;
			}else{
				resultHp.innerText = '사용할 수 있는 번호입니다.';
				resultHp.style.color = 'green';
				isHpOk = true;
			}

		}catch(err){
			console.log(err);
		}
	});


	// 최종 폼 전송 유효성 검사
	registerForm.onsubmit = function(){

		// 아이디 유효성 검사 완료 여부
		if(!isUidOk){
			alert('아이디가 유효하지 않습니다.');
			return false; // 폼 전송 취소
		}

		// 비밀번호 유효성 검사 완료 여부
		if(!isPassOk){
			alert('비밀번호가 유효하지 않습니다.');
			return false; // 폼 전송 취소
		}

		// 이름 유효성 검사 완료 여부
		if(!isNameOk){
			alert('이름이 유효하지 않습니다.');
			return false; // 폼 전송 취소
		}

		// 별명 유효성 검사 완료 여부
		if(!isNickOk){
			alert('별명이 유효하지 않습니다.');
			return false; // 폼 전송 취소
		}

		// 이메일 유효성 검사 완료 여부
		if(!isEmailOk){
			alert('이메일이 유효하지 않습니다.');
			return false; // 폼 전송 취소
		}

		// 휴대폰 유효성 검사 완료 여부
		if(!isHpOk){
			alert('휴대폰 번호가 유효하지 않습니다.');
			return false; // 폼 전송 취소
		}

		return true; // 폼 전송
	}


})
