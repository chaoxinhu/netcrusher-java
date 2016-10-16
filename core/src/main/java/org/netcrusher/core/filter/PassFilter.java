package org.netcrusher.core.filter;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/**
 * Datagram filter
 */
@FunctionalInterface
public interface PassFilter {

    /**
     * <p>Callback that determines if the buffer should be sent.</p>
     * <p><em>Verify that both bb.position() and bb.limit() are properly set after method returns</em></p>
     * @param clientAddress Address of local client socket
     * @param bb Input byte buffer with position set to 0 and limit set to buffer size
     * @return Return true if buffer (datagram) should be sent
     */
    boolean pass(InetSocketAddress clientAddress, ByteBuffer bb);

    /**
     * Chain this filter with other one
     * @param otherFilter Other filter that will be called second
     * @return Combined filter
     */
    default PassFilter then(final PassFilter otherFilter) {
        return (clientAddress, bb) -> this.pass(clientAddress, bb) && otherFilter.pass(clientAddress, bb);
    }

}
