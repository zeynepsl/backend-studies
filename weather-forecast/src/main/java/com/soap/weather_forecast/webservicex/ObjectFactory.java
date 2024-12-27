
package com.soap.weather_forecast.webservicex;

import com.soap.weather_forecast.model.GetCitiesByCountry;
import com.soap.weather_forecast.model.GetCitiesByCountryResponse;
import com.soap.weather_forecast.model.GetWeather;
import com.soap.weather_forecast.model.GetWeatherResponse;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.webservicex package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _String_QNAME = new QName("http://www.webserviceX.NET", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.webservicex
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetWeather }
     * 
     * @return
     *     the new instance of {@link GetWeather }
     */
    public GetWeather createGetWeather() {
        return new GetWeather();
    }

    /**
     * Create an instance of {@link GetWeatherResponse }
     * 
     * @return
     *     the new instance of {@link GetWeatherResponse }
     */
    public GetWeatherResponse createGetWeatherResponse() {
        return new GetWeatherResponse();
    }

    /**
     * Create an instance of {@link GetCitiesByCountry }
     * 
     * @return
     *     the new instance of {@link GetCitiesByCountry }
     */
    public GetCitiesByCountry createGetCitiesByCountry() {
        return new GetCitiesByCountry();
    }

    /**
     * Create an instance of {@link GetCitiesByCountryResponse }
     * 
     * @return
     *     the new instance of {@link GetCitiesByCountryResponse }
     */
    public GetCitiesByCountryResponse createGetCitiesByCountryResponse() {
        return new GetCitiesByCountryResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.webserviceX.NET", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<>(_String_QNAME, String.class, null, value);
    }

}
