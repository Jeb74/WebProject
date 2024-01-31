export function setupSwitchButton()
{
    var index = -1;
    var selected = 0;

    $("#product-buy").click(function() {
        $.ajax({
            url: "./purchase",
            data: {
                id: selected
            },
            dataType: "json",
            success: function() {

            }
        })
    })

    $("#switch-left").click(function() {
        if (index > 0)
        {
            index--
        }

        $.ajax({
            url: "./service",
            data: {
                offset: index
            },
            dataType: "json",
            success: function(item, state) {
                item_display(item)
                selected = item.id
                index = item.offset
            },
            error: function(item, state) {
            }
        })
    })

    $("#switch-right").click(function() {
        index++
        
        $.ajax({
            url: "./service",
            data: {
                offset: index
            },
            dataType: "json",
            success: function(item, state) {
                item_display(item)
                selected = item.id
                index = item.offset
            },
            error: function(item, state) {
            }
        })
    })

    $("#switch-right").click()
}

function item_display(item) 
{
    $("#product-image").prop("src", item.image_path);
    $("#product-image").prop("alt", item.name);
    $("#product-name").html(item.name);
    $("#product-qty-price").html(item.quantity + " items for " + item.price+ "€");
    $("#product-description").html(item.description);
    $("#product-seller-city").html("<b>" + item.seller + "</b>" + " is selling this item in " + "<b>" + item.city + "</b>");
}