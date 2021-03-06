package org.elasticsearch.cassandra.cluster.routing;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.routing.ShardRoutingState;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.transport.TransportAddress;

public class SourceSearchStrategry extends CachedRandomSearchStrategy {
	
	public SourceSearchStrategry() {
		
	}
	
	public class SourceAddressRouter extends CachedRandomRouter {
		public SourceAddressRouter(final String index, final String ksName, final Map<UUID, ShardRoutingState> shardStates, final ClusterState clusterState) {
    		super(index, ksName, shardStates, clusterState);
		}
		
		public Key getKey(TransportAddress src, @Nullable String searchKey, @Nullable String preference) {
	    	return new Key(src, ((preference!=null) && (preference.charAt(0)=='_')) ? preference : null);
	    }
	}
	
	@Override
	public Router newRouter(final String index, final String ksName, final Map<UUID, ShardRoutingState> shardStates, final ClusterState clusterState) {
		return new SourceAddressRouter(index, ksName, shardStates, clusterState);
	}
}
