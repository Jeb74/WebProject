<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object>
    <json:property name="image_path" value="${display.getRelativeItemImage()}" />
    <json:property name="name" value="${display.getItemName()}" />
    <json:property name="description" value="${display.getItemDescription()}" />
    <json:property name="quantity" value="${display.getItemQuantity()}" />
    <json:property name="price" value="${display.getItemPrice()}" />
    <json:property name="seller" value="${display.get('seller').toString()}" />
    <json:property name="city" value="${display.getItemCity()}" />
    <json:property name="id" value="${display.getId()}" />
    <json:property name="offset" value="${display.get('offset').toString()}" />
</json:object>
