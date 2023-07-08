/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package participantes;

import java.util.Objects;
import ma02_resources.participants.Contact;
/**
 * Nome: Rodrigo Bamdé Chantre Lopes
 * Número: 8210191
 * Turma: T4
 * 
 * Nome: Rui Alexande da Silva Oliveira
 * Número: 8210322
 * Turma: T3
 */

/**
 * Classe que define o objeto Contacto
 * Implementa a Interface Contact
 * @author Rodrigo Lopes
 * @author Rui Oliveira
 */
public class Contacto implements Contact{
    /**
     * Variável que guarda a identificação da rua
     */
    private String street;
    /**
     * Variável que guarda a identificação da cidade
     */
    private String city;
    /**
     * Variável que guarda a identificação do estado
     */
    private String state;
    /**
     * Variável que guarda a identificação do código postal
     */
    private String zipCode;
    /**
     * Variável que guarda a identificação do pais
     */
    private String country;
    /**
     * Variável que guarda o número de telefone
     */
    private String phone;

     /**
     * Método construtor para o objeto Contacto
     * @param street Identificação da rua
     * @param city Identificação da cidade
     * @param state Indentificação do estado
     * @param zipcode Identificação xxxxxxxxxxx
     * @param country Identificação do pais
     * @param phone Número de telefone
     */
    public Contacto(String street, String city, String state, String zipCode, String country, String phone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.phone = phone;
    }

    /**
     * Método que retorna a rua
     * @return A identificação da rua
     */
    @Override
    public String getStreet() {
        return street;
    }

    /**
     * Método que retorna a cidade
     * @return A identificação da cidade
     */
    @Override
    public String getCity() {
        return city;
    }

    /**
     * Método que retorna o estado
     * @return A identificação do estado
     */
    @Override
    public String getState() {
        return state;
    }

    /**
     * Método que retorna o código postal
     * @return A identificação do código postal
     */
    @Override
    public String getZipCode() {
        return zipCode;
    }

     /**
     * Método que retorna o pais
     * @return A identificação do pais
     */
    @Override
    public String getCountry() {
        return country;
    }

    /**
     * Método que retorna o número de telefone
     * @return O número de telefone
     */
    @Override
    public String getPhone() {
        return phone;
    }
    
    /**
     * Metodo que verifica se dois objetos são iguais
     * @param obj Objeto a comparar
     * @return True se forem iguais, false caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contacto other = (Contacto) obj;
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return true;
    }  
    
}

