import * as styling from "./styling.js";
import * as viewer from "./item_viewer.js";
import * as sanity from "./sanity.js";

$(document).ready(function() 
{
    setupLogoutButton()

    styling.setupZoomButtons()

    viewer.setupSwitchButton()

    sanity.checkItemName()
    sanity.checkItemDescription()
    sanity.checkQuantity()
    sanity.checkPrice()
    sanity.checkItemCity()

    sanity.checkEmail()
    sanity.checkUsername()
    sanity.checkPassword()

    sanity.submit()
})

function setupLogoutButton() 
{
    $("#logout").click(function() {
        $.ajax({
            url: "./logout",
            type: "POST",
            success: function() {
                window.location.href = "./index.jsp"
            }
        })
    })
}
