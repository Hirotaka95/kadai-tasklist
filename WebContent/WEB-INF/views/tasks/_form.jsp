<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for="task">タスク</label><br/>
<input type="text" name="task" value="${task.content }"/>
<br/>
<input type="hidden" name="_token" value="${_token }"/>
<button type="submit">投稿</button>


