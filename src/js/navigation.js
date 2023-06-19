const cPage = window.location.href;
const navbar = document.getElementsByClassName("navigation")
let pages = navbar[0].getElementsByTagName("a")
for (let i = 0; i < pages.length; i++) {
    if (cPage.endsWith(pages[i].getAttribute("href"))) {
        pages[i].style.backgroundColor = "#2A2A2A"
    }
}


