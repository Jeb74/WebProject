let navReference = null
let contentReference = null
let gNavBarPos = 0

function trackCurrentPage() {
    const cPage = window.location.href;
    let pages = navReference.getElementsByTagName("ul")[0];
    pages = pages.getElementsByTagName("li")
    for (let i = 0; i < pages.length; i++) {
        let val = pages[i].getElementsByTagName("button")[0].getAttribute("value")
        if (cPage.search(val) !== -1) {
            pages[i].style.backgroundColor = "#2A2A2A"
            break
        }
    }
}

function onLoadGetPage() {
    navReference = document.getElementsByClassName("navigation")[0]
    contentReference = document.getElementsByTagName("main")[0]
    gNavBarPos = navReference.offsetTop
    window.onscroll = function() {onScrollSetNav()}
    trackCurrentPage()
}

function onScrollSetNav() {
    if (window.scrollY >= gNavBarPos) {
        navReference.style.position = "fixed"
        contentReference.classList.add("scroll-margin-main")
        return
    }
    navReference.style.position = "sticky";
    contentReference.classList.remove("scroll-margin-main")
}
