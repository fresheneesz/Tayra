import java.net.UnknownHostException;

import com.ee.tayra.fixtures.AssertMongoFixture;
import com.ee.tayra.fixtures.MongoSourceAndTargetConnector;
import com.ee.tayra.fixtures.RunnerFixture;

import fit.Fixture;
import fitlibrary.DoFixture;

public class GivenSourceReplicaSetAndTargetNodeAreRunning extends DoFixture {

  private MongoSourceAndTargetConnector connector;

  public GivenSourceReplicaSetAndTargetNodeAreRunning() {
  }

  public final void withSourceNodeOnPortAndTargetNodeOnPort(
    final String sourceNode, final int sourcePort, final String targetNode,
    final int targetPort) throws UnknownHostException {
    connector = new MongoSourceAndTargetConnector(
      sourceNode, sourcePort, targetNode, targetPort);
  }

  public final Fixture openTerminal() {
     return new RunnerFixture();
  }

  public final Fixture runMongoCommandOn(final String nodeName)
    throws UnknownHostException {
    Node node = Node.valueOf(nodeName.toUpperCase());
    return node.getMongoFixture(connector);
  }

  public final Fixture ensureThat() {
    return new AssertMongoFixture(connector);
  }

  @Override
  protected final void tearDown() throws Exception {
    connector.close();
    super.tearDown();
  }
}