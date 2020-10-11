<#import "commons/common.ftl" as c>

<@c.page>
    <span class="form-inline">
        <h1>Words - ${amount}</h1>
        <a href="/words/new"
           class="btn btn-success"
           role="button"
           style="border-radius: 50%; margin-left: 10px;"><i class="fa fa-plus"></i></a>
    </span>
    <#if words?size != 0>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Original</th>
            <th scope="col">Translation</th>
            <th scope="col"><a href="#" data-toggle="modal" data-target="#showTagList" title="show tag list">Tag</a></th>
        </tr>
        </thead>
        <tbody>
        <#list words as word>
        <tr>
            <td><#if word.original??><a href="/words/${word.id}">${word.original}</a></#if></td>
            <td><#if word.translation??>${word.translation}</#if></td>
            <td>
                <#if word.tags??>
                <span class="with-tooltip"
                      data-toggle="tooltip"
                      data-html="true"
                      title="<#list word.tags as tag><p>${tag.name}</p></#list>">${word.tags?size}</span>
                </#if>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
    </#if>

    <!-- Modal All tags list-->
    <div class="modal fade"
         id="showTagList"
         data-keyboard="false"
         tabindex="-1"
         aria-labelledby="staticBackdropLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"
                        id="staticBackdropLabel">Tags</h5>
                    <button type="button"
                            class="close"
                            data-dismiss="modal"
                            aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <#list tags as tag>
                        <#if tag.name??>
                        <a href="/tags/${tag.id}"
                           class="tag"
                           style="background-color: ${tag.color.getHash()}">${tag.name}</a>
                        </#if>
                    </#list>
                </div>
                <div class="modal-footer">
                    <a href="/tags/new"
                       class="btn btn-success"
                       role="button"
                       style="border-radius: 50%; margin-left: 10px;"><i class="fa fa-plus"></i></a>
                </div>
            </div>
        </div>
    </div>
</@c.page>
