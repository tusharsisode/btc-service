
package com.nab.btc.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Tushar Sisode
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "currency",
    "date",
    "quotes"
})
public class BTC implements Serializable
{

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("date")
    private String date;
    @JsonProperty("quotes")
    private List<Quote> quotes = null;
    private final static long serialVersionUID = -5194931767934051472L;

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("quotes")
    public List<Quote> getQuotes() {
        return quotes;
    }

    @JsonProperty("quotes")
    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("currency", currency).append("date", date).append("quotes", quotes).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(quotes).append(date).append(currency).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BTC) == false) {
            return false;
        }
        BTC rhs = ((BTC) other);
        return new EqualsBuilder().append(quotes, rhs.quotes).append(date, rhs.date).append(currency, rhs.currency).isEquals();
    }

}
