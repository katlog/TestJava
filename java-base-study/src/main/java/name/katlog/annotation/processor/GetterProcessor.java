package name.katlog.annotation.processor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("com.mythsman.test.Getter")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class GetterProcessor extends AbstractProcessor {

    private Messager messager;
//    private JavacTrees trees;
//    private TreeMaker treeMaker;
//    private Names names;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
//        this.messager = processingEnv.getMessager();
//        this.trees = JavacTrees.instance(processingEnv);
//        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
//        this.treeMaker = TreeMaker.instance(context);
//        this.names = Names.instance(context);
    }


    @Override
    public synchronized boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(Getter.class);
//        set.forEach(element -> {
//            JCTree jcTree = trees.getTree(element);
//            jcTree.accept(new TreeTranslator() {
//                @Override
//                public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
//                    List<JCTree.JCVariableDecl> jcVariableDeclList = List.nil();
//
//                    for (JCTree tree : jcClassDecl.defs) {
//                        if (tree.getKind().equals(Tree.Kind.VARIABLE)) {
//                            JCTree.JCVariableDecl jcVariableDecl = (JCTree.JCVariableDecl) tree;
//                            jcVariableDeclList = jcVariableDeclList.append(jcVariableDecl);
//                        }
//                    }
//
//                    jcVariableDeclList.forEach(jcVariableDecl -> {
//                        messager.printMessage(Diagnostic.Kind.NOTE, jcVariableDecl.getName() + " has been processed");
//                        jcClassDecl.defs = jcClassDecl.defs.prepend(makeGetterMethodDecl(jcVariableDecl));
//                    });
//                    super.visitClassDef(jcClassDecl);
//                }
//
//            });
//        });

        return true;
    }

//    private JCTree.JCMethodDecl makeGetterMethodDecl(JCTree.JCVariableDecl jcVariableDecl) {
//
//        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
//        statements.append(treeMaker.Return(treeMaker.Select(treeMaker.Ident(names.fromString("this")), jcVariableDecl.getName())));
//        JCTree.JCBlock body = treeMaker.Block(0, statements.toList());
//        return treeMaker.MethodDef(treeMaker.Modifiers(Flags.PUBLIC), getNewMethodName(jcVariableDecl.getName()), jcVariableDecl.vartype, List.nil(), List.nil(), List.nil(), body, null);
//    }
//
//    private Name getNewMethodName(Name name) {
//        String s = name.toString();
//        return names.fromString("get" + s.substring(0, 1).toUpperCase() + s.substring(1, name.length()));
//    }
}