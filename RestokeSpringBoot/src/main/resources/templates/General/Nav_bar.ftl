<html lang="en">
<#macro title>Nav_bar</#macro>
<#macro content>
    <nav class="sidebar">
        <#if categories??>
            <ul>
            <#list categories as category>
                <li><a href="#" class="${category.str_id}-btn"><i class="fas fa-car"></i>&nbsp${category.name}
                        <span class="fas fa-caret-down third"></span>
                    </a>
                <#if category.subcategoriesNames??>
                    <ul class="${category.str_id}-show">
                        <#list category.subcategoriesNames as subcategory>
                            <li><a href="/restoke_war/subcategories/${subcategory}">${subcategory}</a></li>
                        </#list>
                    </ul>
                </#if>
                </li>
            </#list>
            </ul>
        </#if>
    </nav>
</#macro>
</html>