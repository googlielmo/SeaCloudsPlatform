/**
 * Copyright 2014 SeaClouds
 * Contact: SeaClouds
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package seaclouds.planner;

import seaclouds.utils.toscamodel.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class Planner {
    final private OptimizerExample optimizer = new OptimizerExample();
    final private IToscaEnvironment discoverer = Tosca.newEnvironment();
    final private Matchmaker matchmaker = new Matchmaker(discoverer);

    public Planner() {

    }

    List<IToscaEnvironment> plan(IToscaEnvironment aam) {
        InputStream stream = this.getClass().getResourceAsStream("../../../../aam.yaml");
        aam.readFile(new InputStreamReader(stream));

        Map<String, List<INodeType>> matches = matchmaker.Match(aam);
        return optimizer.optimize(aam, matches);
    }
}