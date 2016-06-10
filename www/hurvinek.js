function deleteComponent(product_id, chapter_id, group_id, component_id, component_name)
{
    var answer = confirm("Do you really want to delete component " + component_name + "?");
    if (answer) {
        window.location = "delete-component?product-id=" + product_id +
                                          "&chapter-id=" + chapter_id +
                                          "&group-id=" + group_id +
                                          "&component-id=" + component_id;
    }
    else {
        return;
    }
}

