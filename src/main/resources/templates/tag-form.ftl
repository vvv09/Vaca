<#import "commons/common.ftl" as c>

<#if update>
    <#assign urlAction>${'/tags/' + tag.id}</#assign>
    <#assign submitTitle>Save</#assign>
    <#assign errorTitle>An error occurred while saving changes!</#assign>
    <#assign title>Edit tag</#assign>
<#else>
    <#assign urlAction>/tags</#assign>
    <#assign submitTitle>Create</#assign>
    <#assign errorTitle>An error occurred while creating new tag!</#assign>
    <#assign title>Creating new tag</#assign>
</#if>

<@c.page>
<style>

</style>

    <div class="py-5 text-center">
        <h2>${title}</h2>
    </div>

    <div class="row">
        <div class="col-md">
            <form class="needs-validation" novalidate action="${urlAction}" method="post" name="tag">
<#--                <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                <#if update><input type="hidden" id="id" class="form-control" name="id" value="${tag.id}"></#if>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="name">Name</label>
                        <input type="text"
                               class="form-control ${(nameError??)?string('is-invalid', '')}"
                               id="name"
                               name="name"
                               placeholder="Enter some name"
                               value="<#if tag.name??>${tag.name}</#if>"
                               required>
                        <div class="invalid-feedback">
                            Valid name is required.
                        </div>
                        <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                        </#if>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="color">Color</label>
                        <select class="custom-select d-block w-100" id="color" name="color" required>
                            <option value="">Choose...</option>
                            <#list colors as color>
                            <option value="${color}" <#if tag.color?? && color == tag.color> selected="selected"</#if>>
                                <span style="background-color: ${color.getHash()}; width: 100%"></span>${color.getName()}</option>
                            </#list>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid color.
                        </div>
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
                                        The Tag will
                                        be permanently removed from the dictionary. Proceed?
                                    </div>
                                </div>
                                <!--Footer-->
                                <div class="modal-footer flex-center">
                                    <a href="/tags/delete/${tag.id}"
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
