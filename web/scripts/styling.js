/**
 * Changes the font size of the "#main-content".
 * @param {*} size The size of the text "font-size".
 */
function modifyFontSize(size) 
{
    $("#main-content").css("font-size", size)
}

/**
 * Binds the actual ZOOM to the buttons "#resizer button".
 */
export function setupZoomButtons() 
{
    $("#resizer button").click(function() {
        modifyFontSize($(this).attr("class"))
    })
}