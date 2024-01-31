const ITEM_NAME_MIN = 4
const ITEM_NAME_MAX = 30
const ITEM_NAME_PATTERN = new RegExp("[\\w'\\s]{" + ITEM_NAME_MIN + "," + ITEM_NAME_MAX + "}")

const ITEM_DESCRIPTION_MIN = ITEM_NAME_MIN
const ITEM_DESCRIPTION_MAX = 150
const ITEM_DESCRIPTION_PATTERN = new RegExp(".{" + ITEM_DESCRIPTION_MIN + "," + ITEM_DESCRIPTION_MAX + "}")

const USERNAME_MIN = 1
const USERNAME_MAX = 30
const USERNAME_PATTERN = new RegExp("^[a-zA-Z0-9_]{1," + USERNAME_MAX + "}$")

const PASSWORD_MIN = 6
const PASSWORD_MAX = 30
const PASSWORD_EASY_PATTERN = new RegExp("[0-9]{5}")

const EMAIL_MIN = 3
const EMAIL_MAX = 320
const EMAIL_PATTERN = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")

const ITEM_CITY_MIN = 2
const ITEM_CITY_MAX = 20
const ITEM_CITY_PATTERN = new RegExp("(?=.*[a-zA-Z])[A-Za-z\\s]{2,20}")

const ITEM_QUANTITY_MIN = 1
const ITEM_QUANTITY_MAX = 999

const PRICE_MIN = 1
const PRICE_MAX = Number.MAX_SAFE_INTEGER

const ALL_PATTERN = /.*/

/**
 * Error messages.
 */
const OUTCOME = {
    RANGE_ZERO: 'The current value must not be zero!',
    RANGE_OVER: 'The current value exceeds the maximum value allowed!',
    RANGE_UNDER: 'The current value is below the minimum allowed!',
    CHARS_ZERO: 'The text must not be null!',
    CHARS_OVER: 'The text length exceeds the maximum value allowed!',
    CHARS_UNDER: 'The text length is below the minimum allowed!',
    INVALID_STRING: 'The text is not valid!',
    INVALID_NUMBER: 'The number is not valid!',
    VALID: ''
}

/**
 * Colors for highlighting.
 */
const COLOR = {
    ERROR: "red",
    DEFAULT: "#D2D6D6"
}

// Global variable to know if all the available inputs are OK.
var inputIsSane

/**
 * Checks if the target length (or value) is less than the min.
 * @param {*} target The target.
 * @param {*} min The minimum length.
 * @returns 
 */
function checkLess(target, min)
{
    if (typeof target === "string")
    {
        return target.length < min
    }

    return target < min
}

/**
 * Checks if the target length (or value) is more than the max.
 * @param {*} target The target.
 * @param {*} max The maximum length.
 * @returns 
 */
function checkMore(target, max)
{
    if (typeof target === "string")
    {
        return target.length > max
    }

    return target > max
}

/**
 * Checks if the target length is zero.
 * @param {*} target The target.
 * @returns 
 */
function checkZero(target)
{
    if (typeof target === "string")
    {
        return target.length === 0
    }

    return target === 0
}

/**
 * Validate the characters of target.
 * @param {*} target The target.
 * @param {*} min_len The minimum length it must have.
 * @param {*} max_len The maximum length it must have.
 * @param {*} regex The regex rules it must respect.
 * @returns OUTCOME
 */
function validateChars(target, min_len, max_len, regex)
{
    if (checkZero(target))
    {
        return OUTCOME.CHARS_ZERO
    }

    if (checkLess(target, min_len))
    {
        return OUTCOME.CHARS_UNDER
    }

    if (checkMore(target, max_len))
    {
        return OUTCOME.CHARS_OVER
    }

    if (regex.test(target))
    {
        return OUTCOME.VALID
    }

    return OUTCOME.INVALID_STRING
}

/**
 * Validate the number of target.
 * @param {*} target The target.
 * @param {*} min The minimum value it must have.
 * @param {*} max The maximum value it must have.
 * @returns OUTCOME
 */
function validateNumer(target, min, max)
{
    if (checkZero(target))
    {
        return OUTCOME.RANGE_ZERO
    }

    if (checkLess(target, min))
    {
        return OUTCOME.RANGE_UNDER
    }

    if (checkMore(target, max))
    {
        return OUTCOME.RANGE_OVER
    }

    return OUTCOME.VALID
}

/**
 * Colors a label.
 * @param {*} label The label to color. 
 * @param {*} color The color to apply.
 */
function color(label, color)
{
    $(label).css("color", color)
}

/**
 * Displays the characters written on top of the input area.
 * @param {*} label The label to write to.
 * @param {*} curr_len The current number of characters written.
 * @param {*} max_len The maximum length of characters that can be written.
 */
function displayCharsLeft(label, curr_len, max_len)
{
    displayMessage(label, "(characters written: " + curr_len + "/" + max_len + ")", curr_len > max_len)
}

function displayMessage(label, message, is_error)
{
    if (message.length === 0)
    {
        $(label).text("")
        return
    }

    $(label).text(" - " + message)
    color(label, is_error ? COLOR.ERROR : COLOR.DEFAULT)
}

class StringValidator 
{
    constructor(target, wordCountLabel, errorLabel, min_len, max_len, regex)
    {
        this.target = $(target)
        this.wordCountLabel = $(wordCountLabel)
        this.errorLabel = $(errorLabel)
        this.min_len = min_len
        this.max_len = max_len
        this.regex = regex
    }
  
    validate() 
    {
        var curr_text = this.target.val()
        var curr_len = curr_text.length

        var outcome = validateChars(curr_text, this.min_len, this.max_len, this.regex)
        var success = outcome === OUTCOME.VALID
    
        displayMessage(this.errorLabel, outcome, !success)
        displayCharsLeft(this.wordCountLabel, curr_len, this.max_len)
    }

    debug()
    {
        console.log("target: " + this.target)
        console.log("wordCountLabel: " + this.wordCountLabel)
        console.log("errorLabel: " + this.errorLabel)
        console.log("min_len: " + this.min_len)
        console.log("max_len: " + this.max_len)
        console.log("regex: " + this.regex)
    }
}

class NumberValidator
{
    constructor(target, errorLabel, min, max)
    {
        this.target = $(target)
        this.errorLabel = $(errorLabel)
        this.min = min
        this.max = max
    }

    validate()
    {
        var curr_num = parseInt(this.target.val())
        var isNumeric = !isNaN(curr_num)

        var outcome = isNumeric ? validateNumer(curr_num, this.min, this.max) : OUTCOME.INVALID_NUMBER
        var success = outcome === OUTCOME.VALID
    
        displayMessage(this.errorLabel, outcome, !success)
    }

    debug()
    {
        console.log("target: " + this.target)
        console.log("errorLabel: " + this.errorLabel)
        console.log("min: " + this.min)
        console.log("max: " + this.max)
    }
}

/**
 * Creates and binds a StringValidator to a target.
 * @param {*} target The target.
 * @param {*} wordCountLabel The word count label.
 * @param {*} errorLabel The error label.
 * @param {*} min_len The minimum length of the word count.
 * @param {*} max_len The maximum length of the word count.
 * @param {*} regex The regex rules that must be respected.
 */
function bindStringValidator(target, wordCountLabel, errorLabel, min_len, max_len, regex)
{
    const validator = new StringValidator(target, wordCountLabel, errorLabel, min_len, max_len, regex)

    if (validator.target.val() === undefined)
    {
        return
    }

    validator.target.ready(function() 
    {
        if (validator.target.val().length > 0)
        {
            validator.validate()
        }
    })

    validator.target.on("input", function() 
    {
        validator.validate()
    })
}

/**
 * Creates and binds a StringValidator to a target.
 * @param {*} target The target.
 * @param {*} wordCountLabel The word count label.
 * @param {*} errorLabel The error label.
 * @param {*} min The minimum length of the word count.
 * @param {*} max The maximum length of the word count.
 */
function bindNumberValidator(target, wordCountLabel, errorLabel, min, max)
{
    const validator = new NumberValidator(target, wordCountLabel, errorLabel, min, max)

    if (validator.target.val() === undefined)
    {
        return
    }

    validator.target.ready(function() 
    {
        if (validator.target.val().length > 0)
        {
            validator.validate()
        }
    })

    validator.target.on("input", function() 
    {
        validator.validate()
    })
}

/**
 * Validation of item name of form in selling.html.
 */
export function checkItemName()
{
    bindStringValidator(
        "#item_name",
        "#wordcount_item_name",
        "#error_item_name",
        ITEM_NAME_MIN,
        ITEM_NAME_MAX,
        ITEM_NAME_PATTERN
    )
}

/**
 * Validation of description of form in selling.html.
 */
export function checkItemDescription() 
{
    bindStringValidator(
        "#item_description",
        "#wordcount_item_description",
        "#error_item_description",
        ITEM_DESCRIPTION_MIN,
        ITEM_DESCRIPTION_MAX,
        ITEM_DESCRIPTION_PATTERN
    )
}

export function checkItemCity()
{
    bindStringValidator(
        "#item_city",
        "#wordcount_item_city",
        "#error_item_city",
        ITEM_CITY_MIN,
        ITEM_CITY_MAX,
        ITEM_CITY_PATTERN
    )
}

/**
 * Validation of price of form in selling.html.
 */
export function checkPrice()
{
    bindNumberValidator(
        "#item_price",
        "#error_item_price",
        PRICE_MIN,
        PRICE_MAX
    )
}

/**
 * Validation of quantity of form in selling.html.
 */
export function checkQuantity()
{
    bindNumberValidator(
        "#item_quantity",
        "#error_item_quantity",
        ITEM_QUANTITY_MIN,
        ITEM_QUANTITY_MAX
    )
}

/**
 * Validation of email of form in register.html and login.html.
 */
export function checkEmail()
{
    bindStringValidator(
        "#email",
        "#wordcount_email",
        "#error_email",
        EMAIL_MIN,
        EMAIL_MAX,
        EMAIL_PATTERN
    )
}

/**
 * Validation of email of form in register.html.
 */
export function checkUsername()
{
    bindStringValidator(
        "#username",
        "#wordcount_username",
        "#error_username",
        USERNAME_MIN,
        USERNAME_MAX,
        USERNAME_PATTERN
    )
}

/**
 * Validation of email of form in register.html and login.html.
 */
export function checkPassword()
{
    bindStringValidator(
        "#password",
        "#wordcount_password",
        "#error_password",
        PASSWORD_MIN,
        PASSWORD_MAX,
        ALL_PATTERN
    )
}