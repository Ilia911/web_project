<c:if test="${not empty requestScope.items}">
        <table>
        <thead>
            <tr>
                <th><locale:loc value="items.name"/></th>
                <th><locale:loc value="items.describe"/></th>
                <th><locale:loc value="items.owner.id"/></th>
                <th><locale:loc value="items.type"/></th>
                <th><locale:loc value="items.price"/></th>
                <th><locale:loc value="items.time"/></th>
                <th><locale:loc value="items.bid.owner.id"/>
                </th><th><locale:loc value="items.do.bid"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${requestScope.items}">
                <tr>
                <td>${item.name}</td>
                <td>${item.describe}</td>
                <td>${item.ownerId}</td>
                <td>${item.type}</td>
                <td>${item.price}</td>
                <td><locale:time value="${item.endTime}"/></td>
                    <c:choose>
                        <c:when test="${item.bidOwnerId eq sessionScope.id}">
                            <td class="bid-owner"><locale:loc value="items.you"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><locale:loc value="items.client"/></td>
                        </c:otherwise>
                    </c:choose>
                <td>
                <form method="post" action="${pageContext.request.contextPath}/controller?command=do_bid">
                    <input name="id" type="hidden" value="${item.id}"/>
                    <input name="itemId" type="hidden" value="${item.itemId}"/>
                    <input name="ownerId" type="hidden" value="${item.ownerId}"/>
                    <input name="previousBidOwnerId" type="hidden" value="${item.bidOwnerId}"/>
                    <input name="type" type="hidden" value="${item.type}"/>
                    <input name="previousPrice" type="hidden" value="${item.price}"/>
                    <input name="previousTime" type="hidden" value="${item.endTime}"/>
                    <input name="bid" type="number" min=1 autofocus/>
                    <input type="submit" value=<locale:loc value="items.do.bid"/>/>
                </form>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="centered">
            <form action="${pageContext.request.contextPath}/controller">
            <input name="command" type="hidden" value="show_lots"/>
            <p><locale:loc value="items.show"/>
                <select name="showedItems">
                    <option value = "${requestScope.showedItems}">${requestScope.showedItems}</option>
                    <option value = "5">5</option>
                    <option value = "10">10</option>
                    <option value = "25">25</option>
                </select>
             <locale:loc value="items.lots"/></p>
        <p><locale:loc value="items.page"/> ${requestScope.showedPage} <locale:loc value="items.from"/>
        ${requestScope.allPages} <locale:loc value="items.pages"/></p>
        <p><input name="showedPage" type="number" min=1 max=${requestScope.allPages} value="${requestScope.showedPage}">
        <input type="submit" value=<locale:loc value="items.show"/>/></p>
        </form>
        </div>
        <br/><br/><br/>
    </c:if>