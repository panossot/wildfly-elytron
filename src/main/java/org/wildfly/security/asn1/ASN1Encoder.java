/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wildfly.security.asn1;

import java.io.Flushable;

import org.wildfly.security.sasl.util.ByteStringBuilder;

/**
 * An interface for encoding ASN.1 values.
 *
 * @author <a href="mailto:fjuma@redhat.com">Farah Juma</a>
 */
public interface ASN1Encoder extends Flushable {

    /**
     * Start encoding an ASN.1 sequence. All subsequent encode operations will be part of
     * this sequence until {@link #endSequence()} is called.
     */
    void startSequence();

    /**
     * Finish encoding an ASN.1 sequence.
     *
     * @throws IllegalStateException if there is no sequence to end
     */
    void endSequence() throws IllegalStateException;

    /**
     * Start encoding an ASN.1 set. All subsequent encode operations will be part of this
     * set until {@link #endSet()} is called.
     */
    void startSet();

    /**
     * Finish encoding an ASN.1 set.
     *
     * @throws IllegalStateException if there is no set to end
     */
    void endSet() throws IllegalStateException;

    /**
     * Encode an ASN.1 octet string value.
     *
     * @param str the octet string to encode
     */
    void encodeOctetString(String str);

    /**
     * Encode an ASN.1 octet string value.
     *
     * @param str the byte array containing the octet string to encode
     */
    void encodeOctetString(byte[] str);

    /**
     * Encode an ASN.1 octet string value.
     *
     * @param str the {@code ByteStringBuilder} containing the octet string to encode
     */
    void encodeOctetString(ByteStringBuilder str);

    /**
     * Encode an ASN.1 IA5 string value.
     *
     * @param str the IA5 string to encode
     */
    void encodeIA5String(String str);

    /**
     * Encode an ASN.1 IA5 string value.
     *
     * @param str the byte array containing IA5 string to encode
     */
    void encodeIA5String(byte[] str);

    /**
     * Encode an ASN.1 IA5 string value.
     *
     * @param str the {@code ByteStringBuilder} containing the IA5 string to encode
     */
    void encodeIA5String(ByteStringBuilder str);

    /**
     * Encode an ASN.1 bit string value.
     *
     * @param str the byte array containing the bit string to encode (all bits in the bit string will be used)
     */
    void encodeBitString(byte[] str);

    /**
     * Encode an ASN.1 bit string value.
     *
     * @param str the byte array containing the bit string to encode
     * @param numUnusedBits the number of unsued bits in the byte array
     */
    void encodeBitString(byte[] str, int numUnusedBits);

    /**
     * Encode an ASN.1 bit string value.
     *
     * @param str the bit string to encode, as a binary string
     */
    void encodeBitString(String binaryStr);

    /**
     * Encode an ASN.1 object identifier value.
     *
     * @param objectIdentifier the object identifier to encode
     * @throws ASN1Exception if the given object identifier is invalid
     */
    void encodeObjectIdentifier(String objectIdentifier) throws ASN1Exception;

    /**
     * Encode an ASN.1 null value.
     */
    void encodeNull();

    /**
     * Flush the encoder, writing any saved ASN.1 encoded values to the target destination.
     * Any unfinished sequences or sets will be ended.
     */
    void flush();
}