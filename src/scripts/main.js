import {modifyFontSize} from "./styling_scripts.js";

function onLoadGetPage() {
    const resizerSection = document.getElementById("resizer")
    const buttons = resizerSection.querySelectorAll("button")

    for (let i = 0; i < buttons.length; i++) {
        buttons[i].onclick = function() {
            modifyFontSize(buttons[i].classList[0])
        }
    }
}

window.onload = function() { onLoadGetPage() }