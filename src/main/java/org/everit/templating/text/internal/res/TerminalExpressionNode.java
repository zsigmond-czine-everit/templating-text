/**
 * This file is part of Everit - Templating Text.
 *
 * Everit - Templating Text is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Templating Text is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Templating Text.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.templating.text.internal.res;

import java.util.Map;

import org.everit.expression.CompiledExpression;
import org.everit.templating.text.internal.CompilableNodeHelper;
import org.everit.templating.util.TemplateWriter;

public class TerminalExpressionNode extends Node {
    private final CompiledExpression ce;

    public TerminalExpressionNode(final Node node, final CompilableNodeHelper helper) {
        this.begin = node.begin;
        this.name = node.name;
        ce = helper.compileExpression(node.contents, node.cStart, node.cEnd - node.cStart);
    }

    @Override
    public boolean demarcate(final Node terminatingNode, final char[] template) {
        return false;
    }

    @Override
    public Object eval(final TemplateWriter appender, final Map<String, Object> vars) {
        Object result = ce.eval(vars);
        appender.append(String.valueOf(result));
        return result;
    }
}
