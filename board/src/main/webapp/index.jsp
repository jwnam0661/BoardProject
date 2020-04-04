<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MyPortal Mock</title>
<script type="text/javascript">
	function onClickHandler(_val) {

		var form = document.forms[0];

		if (_val == '申請者情報登録' ) {

			form['afteraction'].value = 'shinseisha_touroku';

		} else if (_val == '同時申請案件送信' ) {

			form['afteraction'].value = 'submit_douji';

		} else {

			form['afteraction'].value = 'exception';

		}

		form.submit();

	}
</script>
</head>
<body>
	<h2>Hello World!</h2>
	<c:url var="actionUrl" value="/login.do" />
	<form:form action="${actionUrl}" method="post">
		<input type="hidden" name="afteraction" />
		<input type="button" value="申請者情報登録" onclick="return onClickHandler(this.value)" />
		<input type="button" value="同時申請案件送信" onclick="return onClickHandler(this.value)" />
		<input type="submit" value="test" />
	</form:form>
</body>
</html>
