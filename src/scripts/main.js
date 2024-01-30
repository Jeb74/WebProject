import {modifyFontSize} from "./styling_scripts.js";

function setupZoomButtons() 
{
    const resizerSection = document.getElementById("resizer")
    const buttons = resizerSection.querySelectorAll("button")

    buttons.forEach(function(button)
    {
        button.onclick = function() 
        {
            modifyFontSize(button.classList[0])
        }
    })
}

function setupLogoutButton() 
{
    const logoutButton = document.getElementById("logout")

    if (logoutButton == null)
        return;

    logoutButton.onclick = function()
    {
        fetch("./logout", {
            method: "POST",
            credentials: "same-origin"
        })
        .then(result => {
                if (result.ok) 
                {
                    console.log("logged out!")
                    window.location.href = "./index.html";
                }
        })
    }
}

window.onload = function() 
{ 
    setupZoomButtons()
    setupLogoutButton()
}