<%--
  Created by IntelliJ IDEA.
  User: 31165
  Date: 2022/10/19
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<body>

<c:set value="一二三四五六" var="s" />

<form action="givemark/${paper}" method=post>

    <c:set value="1" var="k" />

    <c:forEach items="${disppaper}" var="st">

        <font size=4 face="黑体" color=red>${fn:substring(s,k-1,k)}.${st.name}</font>

        <font size=3 face="宋体" color=green>（每小题${st.score}分）</font>

        <br>

        <c:set value="1" var="x" />

        <c:forEach items="${st.content}" var="content">

            <table width=98% align=center style="word-break: break-all">

                <tr>

                    <td align=left valign=top width=20><pre><b><font color=blue>${x}.</font></b>${fn:replace(content,"<","&lt;")}</pre></td>

                </tr>

            </table>

            <table width=50% align=center>

                <tr>

                    <c:if test="${st.type<=2 }">

                    <c:forTokens items="A,B,C,D,E" delims="," var="item">

                    <td align=right>${item}&nbsp;</td>

                    <td align=left><c:choose>

                        <c:when test="${st.type==1}">

                            <input type=radio size="30" name="data${k}-${x}" value=${item}>

                        </c:when>

                        <c:when test="${st.type==2}">

                            <input type=checkbox size="30" name="data${k}-${x}"

                                   value=${item}>

                        </c:when>

                    </c:choose></td>

                    </c:forTokens>

                    </c:if>

                    <c:if test="${st.type==3}">

                    <TextArea rows="6" cols="40" name="data${k}-${x}"></TextArea>

                    </c:if>

            </table>

            <c:set var="x" value="${x+1}" />

        </c:forEach>

        <c:set var="k" value="${k+1}" />

        <br>

    </c:forEach>

    <p align="center">

        <input type="submit" name="button" value=" 交 卷 ">

</form>

</body>

</html>