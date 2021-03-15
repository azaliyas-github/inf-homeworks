<html lang="en">
<#macro title>Ads block</#macro>
<#macro content>
<div class="block">
    <#if summaryPostings??>
        <#list summaryPostings as summaryPosting>
            <div class="outer_shell">
                <img href="/restoke_war/ad/${summaryPosting.id}" class="inner_photo" src="/restoke_war/photos/${summaryPosting.photoRef}">
                <div class="text_block">
                    <div class="inner_header"><a href="/restoke_war/ad/${summaryPosting.id}">${summaryPosting.header}</a></div>
                    <div class="inner_price">
                        <span>${summaryPosting.price} &#x20bd;</span>
                    </div>
                    <div class="inner_address"><span>${summaryPosting.address}</span></div>
                    <div class="inner_date_of_publishing"><span>${summaryPosting.dateOfPublishing}</span></div>
                </div>
            </div>
        </#list>
    </#if>
</div>
</#macro>
</html>