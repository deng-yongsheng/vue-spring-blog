function hasToken() {
	return localStorage.getItem('username') !== null;
}

function clearToken() {
	return localStorage.clear();
}

function setToken(userid, username) {
	localStorage.setItem('username', username);
	localStorage.setItem('userid', userid);
}

function getToken() {
	if (hasToken()) {
		return localStorage.getItem('username');
	} else {
		throw new Error('没有token');
	}
}

function getUserId() {
	if (hasToken()) {
		return localStorage.getItem('userid');
	} else {
		throw new Error('没有token');
	}
}

function setRedirect(url) {
	sessionStorage.setItem('RedirectUrl', url);
}

function redirect(defaultUrl) {
	var lastUrl = sessionStorage.getItem('RedirectUrl');
	if (lastUrl !== null) {
		sessionStorage.removeItem('RedirectUrl')
		location.href = lastUrl;
	} else {
		location.href = defaultUrl;
	}
}
