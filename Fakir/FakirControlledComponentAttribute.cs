using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Fakir
{

    // The FakirControlledComponentAttribute class is a user-defined attribute class.
    // It can be applied to classes and struct declarations only.
    // It takes one unnamed string argument (the author's name).
    // It has one optional named argument Version, which is of type int.
    [AttributeUsage(AttributeTargets.Class | AttributeTargets.Struct)]
    public class FakirControlledComponentAttribute : Attribute
    {
        // This constructor specifies the unnamed arguments to the attribute class.
        public FakirControlledComponentAttribute()
        {

        }


        // This property is read-write (it has a set accessor)
        // so it can be used as a named argument when using this
        // class as an attribute class.
        public Type[] ListOfThrottledInterfaces
        {
            get 
            {
                return listOfThrottledInterfaces;
            }
            set 
            {
                listOfThrottledInterfaces = value;
            }
        }

        private Type[] listOfThrottledInterfaces;
    }

}
