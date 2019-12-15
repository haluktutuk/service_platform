package com.adenon.api.smpp.wappush;

import java.nio.ByteBuffer;

public class WSPPart extends WDPPart {

    private int    contentType;
    private String contentTypeDescription;


    public WSPPart(String contentTypeDescription,
                   int contentType,
                   short destinationPort,
                   short sourcePort) {
        super(destinationPort, sourcePort);
        this.contentTypeDescription = contentTypeDescription;
        this.contentType = contentType;
    }

    // "application/vnd.wap.sic", 0xAE, -1
    // "application/vnd.wap.slc", 0xB0, -1


    public void encodeWSPBytes(ByteBuffer buffer) throws Exception {
        if (this.contentType > 0) {
            this.encodeKnownContentWSPBytes(buffer);
        } else {
            this.encodeUnknownContentWSPBytes(buffer);
        }
    }

    public int getWSPBytesLength() {
        if (this.contentType > 0) {
            return 4;
        } else {
            return 5 + this.contentTypeDescription.length();
        }

    }

    private void encodeKnownContentWSPBytes(ByteBuffer buffer) throws Exception {
        // WDP (User Data Header):
        buffer.put((byte) 0x01); // Transaction/Push ID
        buffer.put((byte) 0x06); // PDU TYPE
        buffer.put((byte) 0x01); // Headers length
        buffer.put((byte) (0xff & this.getContentType()));
        // buffer.put((byte) 0xb4); //
        // buffer.put((byte) 0x81); //
        // buffer.put((byte) 0xb1); //
        // buffer.put((byte) 0x31); //
        // buffer.put((byte) 0x00); //
    }

    private void encodeUnknownContentWSPBytes(ByteBuffer buffer) throws Exception {
        // WDP (User Data Header):
        buffer.put((byte) 0x01); // Transaction/Push ID
        buffer.put((byte) 0x06); // PDU TYPE
        byte[] bytes = null;
        bytes = this.contentTypeDescription.getBytes("ISO-8859-1");
        int charLength = bytes.length;
        int headerLength = 0;
        if ((charLength + 1) > 30) {
            headerLength = charLength + 3;
        } else {
            headerLength = charLength + 2;
        }
        buffer.put((byte) (headerLength & 0xFF));
        if ((headerLength - 1) > 30) {
            buffer.put((byte) 0x1F);
        }
        buffer.put((byte) ((charLength + 1) & 0xFF));
        buffer.put(bytes);
        buffer.put((byte) 0);
        // Not working part
        // buffer.put((byte) 0x81); // except character set
        // buffer.put((byte) 0xea); // UTF-8
    }

    public int getContentType() {
        return this.contentType;
    }


    public void setContentType(int contentType) {
        this.contentType = contentType;
    }


    public String getContentTypeDescription() {
        return this.contentTypeDescription;
    }


    public void setContentTypeDescription(String contentTypeDescription) {
        this.contentTypeDescription = contentTypeDescription;
    }

}
