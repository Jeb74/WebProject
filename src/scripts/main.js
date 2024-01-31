import { setupZoomButtons } from "./styling_scripts.js";
import { setupSwitchButton } from "./item_viewer.js";

$(document).ready(function() {
    setupZoomButtons()
    setupLogoutButton()
    setupSwitchButton()
})

function setupLogoutButton() 
{
    $("#logout").click(function() {
        $.ajax({
            url: "./logout",
            type: "POST",
            xhrFields: {
                withCredentials: true
            },
            success: function() {
                window.location.href = "./index.html"
            }
        })
    })
}
