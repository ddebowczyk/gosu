/*
 * Copyright 2012. Guidewire Software, Inc.
 */

package gw.internal.gosu.ir.compiler.bytecode.statement;

import gw.internal.gosu.ir.compiler.bytecode.AbstractBytecodeCompiler;
import gw.internal.gosu.ir.compiler.bytecode.IRBytecodeContext;
import gw.internal.gosu.ir.compiler.bytecode.IRBytecodeCompiler;
import gw.lang.ir.statement.IRReturnStatement;
import gw.internal.ext.org.objectweb.asm.Opcodes;

public class IRReturnStatementCompiler extends AbstractBytecodeCompiler {

  public static void compile(IRReturnStatement statement, IRBytecodeContext context) {

    if (statement.getReturnValue() != null) {
      if( statement.hasTempVar() )
      {
        IRBytecodeCompiler.compileIRStatement( statement.getTempVarAssignment(), context );
      }
      else
      {
        IRBytecodeCompiler.compileIRExpression( statement.getReturnValue(), context );
      }
      context.inlineFinallyStatements( statement );
      if( statement.hasTempVar() )
      {
        IRBytecodeCompiler.compileIRExpression(  statement.getReturnValue(), context );
      }
      context.getMv().visitInsn( getIns( Opcodes.IRETURN, statement.getReturnValue().getType() ) );
    } else {
      context.inlineFinallyStatements( statement );
      context.getMv().visitInsn( Opcodes.RETURN );
    }  
  }
}
