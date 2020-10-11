<#import "commons/common.ftl" as c>

<@c.page>
    <span class="d-inline-block"><h1>TAGS</h1><a href="/tags/new" class="btn btn-success" role="button"><i class="fa fa-plus"></i></a></span>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">NAME</th>
        </tr>
        </thead>
        <tbody>
        <#list tags as tag>
        <tr>
            <td>
                <#if tag.name??>
                <span >
                    <a href="/tags/${tag.id}" class="tag" style="background-color: ${tag.color.getHash()}">${tag.name}</a>
                </span>
                </#if>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</@c.page>