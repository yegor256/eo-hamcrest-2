/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Graur Andrew
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
// @checkstyle PackageNameCheck (1 line)
package EOorg.EOeolang.EOhamcrest;

import org.eolang.*;

import java.util.*;

/**
 * Assert-that.equal-to.
 *
 * @since 0.1
 * @checkstyle TypeNameCheck (100 lines)
 */
@SuppressWarnings("PMD.AvoidDollarSigns")
public class EOassert_that extends PhDefault {

    /**
     * Ctor.
     * @param sigma The \sigma
     * @checkstyle BracketsStructureCheck (200 lines)
     */
    @SuppressWarnings("PMD.ConstructorOnlyInitializesOrCallOtherConstructors")
    public EOassert_that(final Phi sigma) {
        super(sigma);
        this.add("reason", new AtFree());
        this.add("actual", new AtFree());
        this.add("args", new AtVararg(/* default */));
        this.add("φ", new AtComposite(this, rho -> {
            final Phi[] args = new Param(rho, "args").strong(Phi[].class);
            boolean result = true;
            int i = 0;

            while (i < args.length) {
                Phi arg = args[i];
                boolean term = new Dataized(arg).take(Boolean.class);
                if (!term) {
                    result = false;
                    break;
                }

                if ("or".equals(arg.getClass().getSimpleName()) && i != args.length - 1) {
                    boolean right = new Dataized(args[i + 1]).take(Boolean.class);
                    i++;
                }
                i++;
            }

            return new Data.ToPhi(result);
        }));
    }
}
