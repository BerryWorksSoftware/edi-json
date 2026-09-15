# EDIReader Framework Community License

Version: 1.0  
Effective date: September 14, 2026
Licensor: R. Scott Mayberry, doing business as BerryWorks Software
Licensing contact: json@canabrook.org

Copyright © 2026 R. Scott Mayberry, doing business as BerryWorks Software. All rights reserved.

## 1. Scope

This license applies to the proprietary EDIReader Framework Community Edition software distributed under the Maven coordinates `com.berryworks:edireader-framework-community`, together with accompanying documentation, except materials expressly provided under separate licenses (the “Software”).

The Software is proprietary. Public availability, free downloading, and publication through GitHub or Maven Central do not make it open-source software or grant rights beyond this license.

“You” means the individual or legal entity exercising rights under this license. An individual acting for an entity must have authority to accept this license on that entity’s behalf.

## 2. Permitted use

Subject to this license, Licensor grants You a nonexclusive, worldwide, royalty-free license to download, install, copy, and use the Software in object-code form for:

1. Personal experimentation, education, evaluation, development, and testing.
2. Evaluating the Software for a proposed product or service that would require a separate commercial license before production use.
3. Developing and operating Your own internal applications and systems, including production systems used in a commercial business.
4. Processing EDI relating to Your own business transactions and operations.

Permitted internal use includes processing transactions exchanged with Your customers, suppliers, insurers, healthcare counterparties, and other trading partners. The involvement of an external trading partner does not, by itself, make a transaction processing service prohibited under Section 4.

These permissions have no evaluation time limit, company-size limit, user-count limit, or transaction-volume limit. They continue for each version received under this license unless terminated under Section 9.

You may make copies reasonably necessary for permitted development, testing, production deployment, disaster recovery, backups, and private artifact repositories.

## 3. Applications, extensions, and contractors

You may use the Software’s documented public interfaces to create applications, wrappers, subclasses, custom adapters, configurations, and model definitions for permitted uses. Such development does not violate Section 5 merely because it links to, calls, or extends a documented public interface.

You retain ownership of Your independently authored application code, transformation mappings, EDI model definitions, and custom adapter logic.

You may engage employees, contractors, and hosting providers to install or operate the Software solely on Your behalf for Your permitted uses. They must comply with this license, may not use Your permission to serve other customers, and may receive access only as necessary to perform their work for You. You remain responsible for their use of the Software on Your behalf.

Running Your internal applications on third-party cloud infrastructure is permitted.

## 4. Uses requiring a separate commercial license

The permissions above do not authorize You to:

1. Use the Software in production to provide EDI conversion, processing, integration, or related functionality for third-party customers or subscribers as part of a hosted service, SaaS offering, API, service bureau, outsourcing arrangement, or managed service.
2. Use the Software to process third parties’ business transactions on their behalf as a service, rather than transactions arising from Your own business dealings with those parties.
3. Sell, rent, sublicense, publish, or otherwise distribute the Software to third parties, either independently or incorporated into another product, application, container image, appliance, or SDK, except for copies provided to contractors or hosting providers solely as permitted under Section 3. Incorporating unmodified Software binaries into applications deployed solely for Your permitted internal use is allowed.
4. Make the Software available for third parties to operate for their own purposes, except for contractors acting solely on Your behalf under Section 3.

These restrictions apply even when the Software is an incidental component of a larger offering, customers cannot access it directly, or no separate fee is charged for its functionality.

For example, a company may use the Software to process invoices it receives from its own suppliers. A provider operating an invoice-processing platform for other companies must obtain a separate commercial license before using the Software in that production service.

Contact Licensor to obtain an Enterprise or other commercial license for uses outside the permissions granted here. Such rights are granted only by a separate written agreement.

## 5. Protection of proprietary implementation

Except as expressly permitted by this license or by applicable law notwithstanding these restrictions, You may not:

1. Reverse engineer, decompile, disassemble, deobfuscate, or otherwise attempt to discover or reconstruct the Software’s source code or proprietary implementation.
2. Modify, translate, adapt, or create derivative versions of the Software’s implementation, including by altering its compiled classes or using reconstructed source code.
3. Remove, obscure, or alter copyright notices, license notices, or other proprietary notices.
4. Direct, authorize, or assist another person to perform any of the prohibited activities above.

The application and extension rights in Section 3 remain permitted. Normal application debugging, profiling, dependency scanning, and examination of documented public interfaces are not prohibited merely because they involve running or inspecting the Software, provided they do not otherwise involve prohibited reconstruction or modification of its implementation.

These restrictions do not limit rights that applicable law makes nonwaivable.

## 6. Third-party and separately licensed materials

The Software depends on EDIReader Core (`com.berryworks:edireader`). For the unmodified Core versions expressly declared as dependencies in the Software’s published POM, Licensor grants You an additional, alternative license to use and copy Core under this license solely in conjunction with permitted use of the Software.

This alternative grant applies only to Core code that Licensor owns or is authorized to license on these terms. It does not revoke, replace, or restrict rights independently available under the GNU General Public License or any other applicable license. It does not grant rights to use the proprietary Framework implementation under those other licenses.

Third-party components remain governed by their respective licenses.

## 7. Ownership and notices

Licensor and its licensors retain ownership of the Software and all associated intellectual property rights. The Software is licensed, not sold.

All rights not expressly granted are reserved. No trademark rights are granted, except the right to identify the Software accurately in descriptions of permitted use.

You must retain this license and accompanying proprietary notices in copies of the Software, including copies maintained in private artifact repositories.

## 8. No support obligation

This license does not obligate Licensor to provide support, maintenance, updates, fixes, continued downloads, or compatibility with future versions.

Support and additional commitments may be available under a separate written agreement.

## 9. Termination

If You materially breach this license and fail to remedy the breach within thirty days after receiving written notice from Licensor, Your rights under this license terminate.

Upon termination, You must stop using the Software and delete copies under Your control, including copies held by contractors on Your behalf. Copies retained in routine backups or legally required archives may remain until removed through ordinary retention processes, but may not be restored or used except as required by law.

Sections concerning restrictions, ownership, disclaimers, liability, and general terms survive termination.

Termination of this license does not terminate rights independently granted under separate licenses for third-party software.

## 10. Disclaimer of warranties

TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, THE SOFTWARE IS PROVIDED “AS IS” AND “AS AVAILABLE,” WITHOUT WARRANTIES OF ANY KIND, EXPRESS, IMPLIED, OR STATUTORY, INCLUDING WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND NONINFRINGEMENT.

LICENSOR DOES NOT WARRANT THAT THE SOFTWARE WILL BE ERROR-FREE, UNINTERRUPTED, SECURE, OR SUITABLE FOR YOUR PARTICULAR REQUIREMENTS, OR THAT CONVERSION RESULTS WILL BE COMPLETE OR CORRECT.

YOU ARE RESPONSIBLE FOR TESTING THE SOFTWARE, REVIEWING ITS OUTPUT, AND DETERMINING WHETHER IT IS APPROPRIATE FOR YOUR USE.

## 11. Limitation of liability

TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, LICENSOR SHALL NOT BE LIABLE FOR INDIRECT, INCIDENTAL, SPECIAL, CONSEQUENTIAL, EXEMPLARY, OR PUNITIVE DAMAGES, OR FOR LOST PROFITS, REVENUE, DATA, OR BUSINESS OPPORTUNITIES ARISING FROM THIS LICENSE OR USE OF THE SOFTWARE, EVEN IF ADVISED OF THEIR POSSIBILITY.

LICENSOR’S TOTAL AGGREGATE LIABILITY ARISING FROM THIS LICENSE OR THE SOFTWARE SHALL NOT EXCEED ONE HUNDRED UNITED STATES DOLLARS (US $100).

NOTHING IN THIS LICENSE EXCLUDES OR LIMITS LIABILITY TO THE EXTENT THAT SUCH EXCLUSION OR LIMITATION IS PROHIBITED BY APPLICABLE LAW.

## 12. General terms

This license is governed by the laws of the State of Tennessee, excluding its conflict-of-laws rules. Disputes shall be subject to the state and federal courts located in Davidson County, Tennessee, subject to mandatory applicable law.

If a provision is unenforceable, it shall be enforced to the extent legally permissible, and the remaining provisions shall remain effective.

A failure to enforce a provision is not a waiver of the right to enforce it later.

This license constitutes the entire agreement concerning the permissions granted here, except where a separate written agreement with Licensor expressly provides otherwise.

Publication of revised terms does not change the license applicable to a version You previously received. Later versions may be offered under different terms.

For permissions beyond this license, contact json@canabrook.org.