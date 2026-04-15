package com.project.shapetool.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

@Endpoint
public class ShapeToolEndpoint {

    private static final String NAMESPACE_URI = "http://shapetool.com/soap";
    private static final double PI = Math.PI;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "circleAreaRequest")
    @ResponsePayload
    public Element circleArea(@RequestPayload Element request) throws Exception {
        double radius = Double.parseDouble(
                request.getElementsByTagNameNS(NAMESPACE_URI, "radius").item(0).getTextContent()
        );

        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }

        double area = PI * radius * radius;
        System.out.println("Circle Area Calculated: radius=" + radius + ", area=" + area);

        return createResponse("circleAreaResponse", area);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "squareAreaRequest")
    @ResponsePayload
    public Element squareArea(@RequestPayload Element request) throws Exception {
        double side = Double.parseDouble(
                request.getElementsByTagNameNS(NAMESPACE_URI, "side").item(0).getTextContent()
        );

        if (side <= 0) {
            throw new IllegalArgumentException("Side must be positive");
        }

        double area = side * side;
        System.out.println("Square Area Calculated: side=" + side + ", area=" + area);

        return createResponse("squareAreaResponse", area);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "rectangleAreaRequest")
    @ResponsePayload
    public Element rectangleArea(@RequestPayload Element request) throws Exception {
        double length = Double.parseDouble(
                request.getElementsByTagNameNS(NAMESPACE_URI, "length").item(0).getTextContent()
        );
        double width = Double.parseDouble(
                request.getElementsByTagNameNS(NAMESPACE_URI, "width").item(0).getTextContent()
        );

        if (length <= 0 || width <= 0) {
            throw new IllegalArgumentException("Length and width must be positive");
        }

        double area = length * width;
        System.out.println("Rectangle Area Calculated: length=" + length + ", width=" + width + ", area=" + area);

        return createResponse("rectangleAreaResponse", area);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "parallelogramAreaRequest")
    @ResponsePayload
    public Element parallelogramArea(@RequestPayload Element request) throws Exception {
        double base = Double.parseDouble(
                request.getElementsByTagNameNS(NAMESPACE_URI, "base").item(0).getTextContent()
        );
        double height = Double.parseDouble(
                request.getElementsByTagNameNS(NAMESPACE_URI, "height").item(0).getTextContent()
        );

        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and height must be positive");
        }

        double area = base * height;
        System.out.println("Parallelogram Area Calculated: base=" + base + ", height=" + height + ", area=" + area);

        return createResponse("parallelogramAreaResponse", area);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "triangleAreaRequest")
    @ResponsePayload
    public Element triangleArea(@RequestPayload Element request) throws Exception {
        double base = Double.parseDouble(
                request.getElementsByTagNameNS(NAMESPACE_URI, "base").item(0).getTextContent()
        );
        double height = Double.parseDouble(
                request.getElementsByTagNameNS(NAMESPACE_URI, "height").item(0).getTextContent()
        );

        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and height must be positive");
        }

        double area = 0.5 * base * height;
        System.out.println("Triangle Area Calculated: base=" + base + ", height=" + height + ", area=" + area);

        return createResponse("triangleAreaResponse", area);
    }

    private Element createResponse(String elementName, double area)
            throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element response = doc.createElementNS(NAMESPACE_URI, elementName);
        Element areaElement = doc.createElementNS(NAMESPACE_URI, "area");
        areaElement.setTextContent(String.valueOf(area));
        response.appendChild(areaElement);

        return response;
    }
}