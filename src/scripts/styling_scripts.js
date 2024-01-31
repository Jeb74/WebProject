function modifyFontSize(size) 
{
    $("#main-content").css("font-size", size)
}

export function setupZoomButtons() 
{
    $("#resizer button").click(function() {
        modifyFontSize($(this).attr("class"))
    })
}