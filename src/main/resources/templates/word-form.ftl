<#import "commons/common.ftl" as c>

<#if update>
    <#assign urlAction>${'/words/' + word.id}</#assign>
    <#assign submitTitle>Save</#assign>
    <#assign errorTitle>An error occurred while saving changes!</#assign>
    <#assign title>Edit word</#assign>
<#else>
    <#assign urlAction>/words</#assign>
    <#assign submitTitle>Create</#assign>
    <#assign errorTitle>An error occurred while adding new word!</#assign>
    <#assign title>Adding new word</#assign>
</#if>

<@c.page>
    <style>

    </style>
    <#if message??>
    <div class="alert <#if messageType??>alert-${messageType}</#if>"
         role="alert">${message}</div>
    </#if>

    <div class="py-5 text-center">
        <h2>${title}</h2>
    </div>

    <div class="row">
        <div class="col-md">
            <form class="needs-validation" novalidate action="${urlAction}" method="post" name="word">
                <#--                <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                <#if update><input type="hidden" id="id" class="form-control" name="id" value="${word.id}"></#if>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="original">Original</label>
                        <input type="text"
                               class="form-control ${(originalError??)?string('is-invalid', '')}"
                               id="original"
                               name="original"
                               placeholder="Enter some name"
                               value="<#if word.original??>${word.original}</#if>"
                               required>
                        <div class="invalid-feedback">
                            Valid original is required.
                        </div>
                        <#if originalError??>
                            <div class="invalid-feedback">
                                ${originalError}
                            </div>
                        </#if>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="original">Translation</label>
                        <input type="text"
                               class="form-control ${(translationError??)?string('is-invalid', '')}"
                               id="original"
                               name="translation"
                               placeholder="Enter some name"
                               value="<#if word.translation??>${word.translation}</#if>"
                               required>
                        <div class="invalid-feedback">
                            Valid translatiom is required.
                        </div>
                        <#if translationError??>
                            <div class="invalid-feedback">
                                ${translationError}
                            </div>
                        </#if>
                    </div>
                </div>
                <hr class="mb-4">
                <h4 class="mb-3">Payment</h4>
                <p class="lead">Add one or more tags to make the process of memorising easier</p>
                <div class="row">
                    <div class="col mb-3">
                        <#list tags as tag>
                        <span class="custom-control custom-checkbox custom-control-inline m-4">
                            <input type="checkbox"
                                   class="custom-control-input"
                                   id="id_${tag.name}"
                                   name="${tag.name}"
                                   <#if word.tags??>${word.tags?seq_contains(tag)?string("checked", "")}</#if>>
                            <label class="custom-control-label" for="id_${tag.name}">
                               <a href="#"
                                  class="tag"
                                  style="background-color: ${tag.color.getHash()}">${tag.name}</a>
                            </label>
                        </span>
                        </#list>
                        <a href="/tags/new"
                           class="btn btn-success"
                           role="button"
                           style="border-radius: 50%; margin-left: 10px;"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <hr class="mb-4">
                <button class="btn btn-primary btn-md" type="submit">${submitTitle}</button>
                <a class="btn btn-info btn-md" href="/words">Back</a>
                <#if update>
                    <a class="btn btn-outline-danger btn-md"
                       href="#"
                       data-toggle="modal"
                       data-target="#modalConfirmDelete"">Delete</a>

                    <!--Modal: modalConfirmDelete-->
                    <div class="modal fade" id="modalConfirmDelete" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-sm modal-notify" role="document">
                            <!--Content-->
                            <div class="modal-content text-center">
                                <!--Header-->
                                <div class="modal-header d-flex justify-content-center align-items-center" style="background-color: #dc3545;">
                                    <span class="heading" style="font-size: 1.15rem; color: #fff;">Delete confirmation</span>
                                </div>
                                <!--Body-->
                                <div class="modal-body">
                                    <i class="fas fa-times fa-4x animated rotateIn" style="color: #dc3545;" ></i>
                                    <div class="modal-body">
                                        The word will
                                        be permanently removed from the dictionary. Proceed?
                                    </div>
                                </div>
                                <!--Footer-->
                                <div class="modal-footer flex-center">
                                    <a href="/words/delete/${word.id}"
                                       class="btn btn-outline-danger">Yes</a>
                                    <a type="button" class="btn btn-danger waves-effect"
                                       data-dismiss="modal">No</a>
                                </div>
                            </div>
                            <!--/.Content-->
                        </div>
                    </div>
                    <!--Modal: modalConfirmDelete-->
                </#if>
            </form>
        </div>
    </div>
</@c.page>


