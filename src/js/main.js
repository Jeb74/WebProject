import {onScrollSetNav, trackCurrentPage} from "./navigation.js";
import {modifyFontSize} from "./styling_scripts.js";

function onLoadGetPage() {
    window.onscroll = function() {onScrollSetNav()}
    trackCurrentPage()
    const buttons = document.getElementsByClassName("letter-resizer")
    for (let i = 0; i < buttons.length; i++) {
        switch (buttons[i].classList[0]) {
            case "normal": buttons[i].onclick = function() {modifyFontSize("medium")}
                break
            case "large": buttons[i].onclick = function() {modifyFontSize("large")}
                break
            case "xlarge": buttons[i].onclick = function() {modifyFontSize("x-large")}
                break
        }
    }
}

window.onload = function() {onLoadGetPage()}